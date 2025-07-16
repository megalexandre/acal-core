package acal.com.core.application.invoice

import acal.com.core.domain.entity.Invoice
import acal.com.core.domain.usecase.invoice.InvoicePaginateUseCase
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
) {

    @PostMapping("/paginate")
    @ResponseStatus(HttpStatus.OK)
    fun get(@RequestBody filter: InvoiceFilter): Page<Invoice> =
        paginate.execute(filter)

}
