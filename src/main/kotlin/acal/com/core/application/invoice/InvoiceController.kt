package acal.com.core.application.invoice

import acal.com.core.application.invoice.`in`.InvoiceCreateRequest
import acal.com.core.application.invoice.`in`.toDomain
import acal.com.core.application.invoice.out.InvoiceViewResponse
import acal.com.core.application.invoice.out.response
import acal.com.core.application.invoice.out.toPDF
import acal.com.core.application.invoice.out.toView
import acal.com.core.comons.current
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
import acal.com.core.infrastructure.serializer.Report
import org.springframework.data.domain.Page
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

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

    @PostMapping("/print", produces = [MediaType.APPLICATION_PDF_VALUE, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"])
    @ResponseStatus(HttpStatus.OK)
    fun print(
        @RequestBody filter: InvoiceFilter,
        @RequestParam(defaultValue = "PDF") format: ReportType = ReportType.PDF
    ): ResponseEntity<ByteArray> {

        val headers = HttpHeaders().apply {
            contentType = MediaType.APPLICATION_PDF
        }

        val fileName = "invoice_report_${LocalDateTime.now().current()}.pdf"
        headers[HttpHeaders.CONTENT_DISPOSITION] = "attachment; filename=$fileName"

        return ResponseEntity(reportService.generate(
            data = findAll.execute(filter).toPDF(),
            report = Report.INVOICE,
        ), headers, HttpStatus.OK)
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
