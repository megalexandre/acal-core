package acal.com.core.domain.usecase.invoice

import acal.com.core.domain.datasource.InvoiceDataSource
import acal.com.core.domain.entity.Invoice
import org.springframework.stereotype.Component

@Component
class InvoiceCreateUseCase(
    private val dataSource: InvoiceDataSource
) {
    fun execute(invoices: Collection<Invoice>): Collection<Invoice> {
        return dataSource.save(invoices.map {
            Invoice(
                id = it.id,
                reference = it.reference,
                number = it.number,
                waterMeter = it.waterMeter,
                customer = it.customer,
                place = it.place,
                category = it.category,
                dueDate = it.dueDate,
                paidAt = null
            )
        })
    }
}
