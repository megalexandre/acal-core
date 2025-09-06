package acal.com.core.application.financialregister

import acal.com.core.application.financialregister.data.out.FinancialRecordResponse
import acal.com.core.application.financialregister.data.out.toPage
import acal.com.core.application.financialregister.data.out.toPdf
import acal.com.core.comons.currency
import acal.com.core.comons.current
import acal.com.core.domain.usecase.financialrecord.FinancialRecordFindAllUseCase
import acal.com.core.domain.usecase.financialrecord.FinancialRecordPaginateUseCase
import acal.com.core.domain.valueobject.FinancialRecordFilter
import acal.com.core.infrastructure.serializer.JasperReportService
import acal.com.core.infrastructure.serializer.Report
import acal.com.core.infrastructure.serializer.ReportType
import org.springframework.data.domain.Page
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping(
    value = ["/financial-record"],
)
class FinancialRecordController(
    private val paginate: FinancialRecordPaginateUseCase,
    private val findAll: FinancialRecordFindAllUseCase,
    private val reportService: JasperReportService
) {

    @PostMapping("/paginate")
    @ResponseStatus(HttpStatus.OK)
    fun paginate(@RequestBody filter: FinancialRecordFilter): Page<FinancialRecordResponse> =
        paginate.execute(filter).toPage()

    @PostMapping("/print", produces = [MediaType.APPLICATION_PDF_VALUE])
    @ResponseStatus(HttpStatus.OK)
    fun print(
        @RequestBody filter: FinancialRecordFilter,
        @RequestParam(defaultValue = "PDF") format: ReportType = ReportType.PDF
    ): ResponseEntity<ByteArray> {

        val headers = HttpHeaders().apply {
            contentType = MediaType.APPLICATION_PDF
        }

        val fileName = "financial_report_${LocalDateTime.now().current()}.pdf"
        headers[HttpHeaders.CONTENT_DISPOSITION] = "attachment; filename=$fileName"

        val data = findAll.execute(filter)
        val parameters = mapOf(
            "TOTAL" to data.map { it.detail }.sumOf { it.total }.currency()
        )
        return ResponseEntity(reportService.generate(
            data = data.toPdf(),
            report = Report.FINANCIAL_RECORD,
            parameters = parameters,
        ), headers, HttpStatus.OK)
    }
}
