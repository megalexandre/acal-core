package acal.com.core.domain.usecase.invoice

import acal.com.core.domain.datasource.InvoiceDataSource
import acal.com.core.domain.entity.Invoice
import acal.com.core.domain.entity.InvoiceNumber
import org.springframework.stereotype.Component

@Component
class InvoiceCreateUseCase(
    private val dataSource: InvoiceDataSource
) {
    fun execute(invoices: Collection<Invoice>): Collection<Invoice> =
        dataSource.save(invoices
            .groupBy { it.reference }
            .flatMap { (reference, group) ->
                var invoiceNumber = InvoiceNumber(reference, maxOf(1,dataSource.countByReferencesContaining(reference) ) )
                group.map { invoice ->
                    invoice.copy(
                        number = invoiceNumber.value,
                        paidAt = null
                    ).also { invoiceNumber = invoiceNumber.next }
                }
            })

}
