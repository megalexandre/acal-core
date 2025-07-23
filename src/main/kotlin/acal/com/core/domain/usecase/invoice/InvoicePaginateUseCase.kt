package acal.com.core.domain.usecase.invoice

import acal.com.core.domain.datasource.InvoiceDataSource
import acal.com.core.domain.entity.Invoice
import acal.com.core.domain.valueobject.InvoiceFilter
import org.springframework.data.domain.Page
import org.springframework.stereotype.Component

@Component
class InvoicePaginateUseCase(
    private val dataSource: InvoiceDataSource
) {
    fun execute(filter: InvoiceFilter): Page<Invoice> =
        dataSource.paginate(filter)
}
