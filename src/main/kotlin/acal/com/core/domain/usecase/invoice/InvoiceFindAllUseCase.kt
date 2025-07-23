package acal.com.core.domain.usecase.invoice

import acal.com.core.domain.datasource.InvoiceDataSource
import acal.com.core.domain.entity.Invoice
import org.springframework.stereotype.Component

@Component
class InvoiceFindAllUseCase(
    private val dataSource: InvoiceDataSource,
) {
    fun execute(): Collection<Invoice> =
        dataSource.findAll()
}
