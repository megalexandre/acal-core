package acal.com.core.application.waterMeter.data.`in`

import acal.com.core.comons.Id
import acal.com.core.domain.entity.Reference
import acal.com.core.domain.entity.WaterMeter
import java.math.BigDecimal

data class WaterMeterCreateRequest(
    val id: String?,
    val reference: Reference,
    val linkId: String,
    val start: Double,
    val end: Double,
    val value: BigDecimal,
    val freeTier: Double
) {
    fun toDomain() = WaterMeter(
        id = id ?: Id.random(),
        reference = reference,
        linkId = linkId,
        start = start,
        end = end,
        value = value,
        freeTier = freeTier
    )
}
