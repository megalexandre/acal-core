package acal.com.core.application.invoice

import acal.com.core.application.invoice.data.`in`.InvoiceCreateRequest
import acal.com.core.application.invoice.data.`in`.toDomain
import acal.com.core.application.invoice.data.out.InvoiceViewResponse
import acal.com.core.application.invoice.data.out.response
import acal.com.core.application.invoice.data.out.toView
import acal.com.core.infrastructure.serializer.JasperReportService
import acal.com.core.infrastructure.serializer.ReportType
import acal.com.core.domain.entity.Reference
import acal.com.core.domain.usecase.invoice.InvoiceCancelPaymentUseCase
import acal.com.core.domain.usecase.invoice.InvoiceCreateUseCase
import acal.com.core.domain.usecase.invoice.InvoiceDeleteUseCase
import acal.com.core.domain.usecase.invoice.InvoiceFindAllUseCase
import acal.com.core.domain.usecase.invoice.InvoicePaginateUseCase
import acal.com.core.domain.usecase.invoice.InvoicePayUseCase
import acal.com.core.domain.usecase.invoice.InvoicePreviewUseCase
import acal.com.core.domain.valueobject.InvoiceFilter
import org.springframework.data.domain.Page
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RestController
@RequestMapping(
    value = ["/invoice"],
)
class InvoiceController(
    private val paginate: InvoicePaginateUseCase,
    private val preViewInvoice: InvoicePreviewUseCase,
    private val invoiceCreate: InvoiceCreateUseCase,
    private val pay: InvoicePayUseCase,
    private val cancelPayment: InvoiceCancelPaymentUseCase,
    private val delete: InvoiceDeleteUseCase,
    private val findAll: InvoiceFindAllUseCase,
    private val reportService: JasperReportService
) {

    @GetMapping("/preview/{reference}")
    fun view(@PathVariable reference: Reference): Collection<InvoiceViewResponse> =
        preViewInvoice.execute(reference).toView()

    @PostMapping("/all")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody request: Collection<InvoiceCreateRequest>) =
        invoiceCreate.execute(request.toDomain())

    @PostMapping("/paginate")
    @ResponseStatus(HttpStatus.OK)
    fun paginate(@RequestBody filter: InvoiceFilter): Page<InvoiceViewResponse> =
        paginate.execute(filter).response()

    @PostMapping("/print", produces = [MediaType.APPLICATION_PDF_VALUE])
    @ResponseStatus(HttpStatus.OK)
    fun print(@RequestBody filter: InvoiceFilter): ResponseEntity<ByteArray> {
        val invoices = findAll.execute(filter)

        val reportBytes = reportService.generateReport(
            reportName = "invoice",
            reportData = invoices,
            parameters = mapOf()
        )

        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_PDF
        headers[HttpHeaders.CONTENT_DISPOSITION] = "attachment; filename=invoice_report.pdf"
        return ResponseEntity(reportBytes, headers, HttpStatus.OK)
    }

    @PostMapping("/print/excel", produces = ["application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"])
    @ResponseStatus(HttpStatus.OK)
    fun printExcel(@RequestBody filter: InvoiceFilter): ResponseEntity<ByteArray> {
        val invoices = findAll.execute(filter)

        val parameters = mapOf(
            "REPORT_TITLE" to "Relatório de Faturas",
            "GENERATED_DATE" to LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
            "COMPANY_NAME" to "ACAL"
        )

        val reportBytes = reportService.generateReport(
            reportName = "invoice",
            reportData = invoices,
            parameters = parameters,
            reportType = ReportType.XLSX
        )

        val headers = HttpHeaders()
        headers.contentType = MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=invoice_report.xlsx")

        return ResponseEntity(reportBytes, headers, HttpStatus.OK)
    }

    @PostMapping("/pay/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun pay(@PathVariable id: String) =
        pay.execute(id)

    @PostMapping("/cancel-payment/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun cancelPayment(@PathVariable id: String) =
        cancelPayment.execute(id)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun delete(@PathVariable id: String) =
        delete.execute(id)

}
