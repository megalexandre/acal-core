package acal.com.core.domain.entity

import java.math.BigDecimal

class WaterMeter(
    val start: Double,
    val end: Double,
    val value: BigDecimal
) {

    val consumption: Double
        get() = maxOf(0.0, end - start)

    val total: BigDecimal
        get() = value.multiply(value)

}
