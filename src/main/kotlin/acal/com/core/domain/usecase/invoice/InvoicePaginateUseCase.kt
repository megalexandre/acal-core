package acal.com.core.domain.usecase.invoice

import acal.com.core.domain.datasource.InvoiceDataSource
import acal.com.core.domain.datasource.WaterQualityDataSource
import acal.com.core.domain.entity.Invoice
import acal.com.core.domain.entity.Reference
import acal.com.core.domain.valueobject.InvoiceFilter
import org.springframework.data.domain.Page
import org.springframework.stereotype.Component

@Component
class InvoicePaginateUseCase(
    private val dataSource: InvoiceDataSource,
    private val waterQualityDateSource: WaterQualityDataSource
) {
    fun execute(filter: InvoiceFilter): Page<Invoice> {

        val paginate = dataSource.paginate(filter)

        val references: Collection<Reference> = paginate.map { it.reference }.toList()
        val waterQualities = waterQualityDateSource.findByReferences(references)

        paginate.map {
            it.waterQuality = waterQualities.firstOrNull { waterQuality -> waterQuality.reference == it.reference }
        }

        return paginate
    }

}
