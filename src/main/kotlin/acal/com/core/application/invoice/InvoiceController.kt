package acal.com.core.application.invoice

import acal.com.core.application.invoice.data.`in`.InvoiceCreateRequest
import acal.com.core.application.invoice.data.`in`.toDomain
import acal.com.core.application.invoice.data.out.InvoiceViewResponse
import acal.com.core.application.invoice.data.out.response
import acal.com.core.application.invoice.data.out.toView
import acal.com.core.domain.entity.Invoice
import acal.com.core.domain.entity.Reference
import acal.com.core.domain.usecase.invoice.InvoiceCreateUseCase
import acal.com.core.domain.usecase.invoice.InvoiceFindAllUseCase
import acal.com.core.domain.usecase.invoice.InvoicePaginateUseCase
import acal.com.core.domain.usecase.invoice.InvoicePreviewUseCase
import acal.com.core.domain.valueobject.InvoiceFilter
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(
    value = ["/invoice"],
)
class InvoiceController(
    private val paginate: InvoicePaginateUseCase,
    private val preViewInvoice: InvoicePreviewUseCase,
    private val invoiceCreate: InvoiceCreateUseCase,
    private val invoiceFindAll: InvoiceFindAllUseCase,

) {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun get(): Collection<Invoice> =
        invoiceFindAll.execute()

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

}
