package acal.com.core.domain.usecase.invoice

import acal.com.core.comons.Id
import acal.com.core.domain.datasource.InvoiceDataSource
import acal.com.core.domain.datasource.LinkDataSource
import acal.com.core.domain.entity.Invoice
import acal.com.core.domain.entity.Reference
import acal.com.core.domain.entity.WaterMeter
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.time.LocalDate

@Component
class InvoicePreviewUseCase(
    private val dataSource: InvoiceDataSource,
    private val linkDataSource: LinkDataSource,
) {
    fun execute(reference: Reference): Collection<Invoice> {
        val findActiveLinksWithoutReference = linkDataSource.findActiveLinksWithoutReference(reference)

        var countByReferencesContaining = dataSource.countByReferencesContaining(reference)

        val waterMeter = WaterMeter(
            start = 0.0,
            end = 0.0,
            value = BigDecimal(0.04)
        )
        return findActiveLinksWithoutReference.map {
            Invoice(
                id = Id.random(),
                reference = reference,
                number = reference.toString() + "-" + (++countByReferencesContaining),
                waterMeter = waterMeter,
                customer = it.customer,
                place = it.place,
                category = it.category,
                dueDate = LocalDate.MIN,
                paidAt = null,
                waterQuality = null
            )
        }.sortedWith(
            compareBy(
                { it.place.name },
                { it.place.number },
            )
        )
    }
}
