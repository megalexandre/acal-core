package acal.com.core.domain.entity

import java.math.BigDecimal

class WaterMeter(
    val id:String,
    val reference: Reference,
    val linkId: String,
    val linkName: String,
    val start: Double,
    val end: Double,
    val pricePerUnit: BigDecimal,
    val freeTier: Double
) {

    val paidUsageValue: Double
        get() = maxOf(0.0, (end - start) - freeTier )

    val consumptionTotal: Double
        get() = maxOf(0.0, end - start )

    val total: BigDecimal
        get() = pricePerUnit.multiply(BigDecimal.valueOf(paidUsageValue))

}
