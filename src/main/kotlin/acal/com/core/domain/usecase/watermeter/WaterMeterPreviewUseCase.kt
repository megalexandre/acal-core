package acal.com.core.domain.usecase.watermeter

import acal.com.core.comons.Id
import acal.com.core.domain.datasource.LinkDataSource
import acal.com.core.domain.datasource.WaterMeterDataSource
import acal.com.core.domain.entity.Link
import acal.com.core.domain.entity.Reference
import acal.com.core.domain.entity.WaterMeter
import acal.com.core.domain.entity.WaterMeterPreview
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class WaterMeterPreviewUseCase(
    private val dataSource: WaterMeterDataSource,
    private val linkDataSource: LinkDataSource,


    @Value("\${prices.water.free-tier}") private val freeTier: Double,
    @Value("\${prices.water.price-per-unit}") private val pricePerUnit: Double
) {

    fun execute(reference: Reference): Collection<WaterMeterPreview> {
        val itemsByLinksId = dataSource.findAll(reference).associateBy { it.linkId }
        val lastMouthItems = dataSource.findAll(reference.lastMonth()).associateBy { it.linkId }

        return linkDataSource.findActive()
            .map { link ->
                val meter = itemsByLinksId[link.id] ?: waterMeterBuild(link, reference, lastMouthItems)

                WaterMeterPreview(
                    id = meter.id,
                    reference = reference,
                    linkId = link.id,
                    linkName = link.name,
                    start = meter.start,
                    end = meter.end,
                    pricePerUnit = BigDecimal.valueOf(pricePerUnit),
                    freeTier = freeTier
                )
            }
    }

    private fun waterMeterBuild(link: Link, reference: Reference, lastMouthItems: Map<String, WaterMeter> ): WaterMeter {
        return WaterMeter(
            id = Id.random(),
            linkId = link.id,
            linkName = link.name,
            reference = reference,
            start = lastMouthItems[link.id]?.end ?: 0.0,
            end = 0.0,
            pricePerUnit = BigDecimal.valueOf(pricePerUnit),
            freeTier = freeTier
        )
    }
}
