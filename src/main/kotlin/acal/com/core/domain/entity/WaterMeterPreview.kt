package acal.com.core.domain.entity

import java.math.BigDecimal

class WaterMeterPreview(
    val id: String,
    val reference: Reference,
    val linkId: String,
    val linkName: String,
    val start: Double,
    val end: Double,

    val pricePerUnit: BigDecimal,
    val freeTier: Double,
)
