package acal.com.core.application.invoice

import acal.com.core.domain.entity.Invoice
import acal.com.core.domain.entity.Reference
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
) {

    @PostMapping("/paginate")
    @ResponseStatus(HttpStatus.OK)
    fun get(@RequestBody filter: InvoiceFilter): Page<Invoice> =
        paginate.execute(filter)

    @GetMapping("/preview/{reference}")
    fun view(@PathVariable reference: Reference): Collection<Invoice> {
        return preViewInvoice.execute(reference)
    }

    /*
    @PostMapping
    @ResponseStatus(CREATED)
    fun create(@RequestBody request: LinkCreateRequest): LinkResponse =
        linkCreateUseCase.execute(request.toDomain()).response()

    @PostMapping("/all")
    @ResponseStatus(CREATED)
    fun create(@RequestBody request: Collection<LinkCreateRequest>): Collection<LinkResponse> =
        request.map { linkCreateUseCase.execute(it.toDomain())}.response()

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun get(@PathVariable id: String): LinkResponse? =
        linkByIdUseCase.execute(id)?.response()
    */
}
