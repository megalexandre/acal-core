package acal.com.core.domain.usecase.invoice

import acal.com.core.domain.datasource.InvoiceDataSource
import acal.com.core.domain.datasource.WaterQualityDataSource
import acal.com.core.domain.entity.Invoice
import acal.com.core.domain.entity.Reference
import acal.com.core.domain.valueobject.InvoiceFilter
import org.springframework.stereotype.Component

@Component
class InvoiceFindAllUseCase(
    private val dataSource: InvoiceDataSource,
    private val waterQualityDateSource: WaterQualityDataSource
) {
    fun execute(filter: InvoiceFilter): Collection<Invoice> {

        val invoices = dataSource.findAll(filter)

        val references: Collection<Reference> = invoices.map { it.reference }.toList()
        val waterQualities = waterQualityDateSource.findByReferences(references)

        invoices.map {
            it.waterQuality = waterQualities.firstOrNull { waterQuality -> waterQuality.reference == it.reference }
        }

        return invoices
    }

}
