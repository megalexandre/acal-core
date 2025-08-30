package acal.com.core.domain.entity

import java.math.BigDecimal

class WaterMeter(
    val start: Double,
    val end: Double,
    val value: BigDecimal,
    val freeTier: Double = 1000.0
) {

    val paidUsageValue: Double
        get() = maxOf(0.0, (end - start) - freeTier )

    val consumptionTotal: Double
        get() = maxOf(0.0, end - start )

    val total: BigDecimal
        get() = value.multiply(BigDecimal.valueOf(paidUsageValue))

}
