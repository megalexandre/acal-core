package acal.com.core.domain.usecase.invoice

import acal.com.core.comons.Id
import acal.com.core.domain.datasource.InvoiceDataSource
import acal.com.core.domain.datasource.LinkDataSource
import acal.com.core.domain.entity.Invoice
import acal.com.core.domain.entity.Reference
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class InvoiceViewUseCase(
    private val dataSource: InvoiceDataSource,
    private val linkDataSource: LinkDataSource,
) {
    fun execute(reference: Reference): Collection<Invoice> {
        val findActiveLinksWithoutReference = linkDataSource.findActiveLinksWithoutReference(reference)

        var countByReferencesContaining = dataSource.countByReferencesContaining(reference)

        return findActiveLinksWithoutReference.map {
            Invoice(
                id = Id.random(),
                reference = reference,
                number = reference.toString() + "-" + (++countByReferencesContaining),
                waterMeter = null,
                customer = it.customer,
                place = it.place,
                category = it.category,
                dueDate = LocalDate.MIN,
                paidAt = null
            )
        }.sortedWith(
            compareBy(
                { it.place.address },
                { it.place.number },
            )
        )
    }
}
