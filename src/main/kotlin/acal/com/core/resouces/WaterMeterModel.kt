package acal.com.core.resouces

import acal.com.core.domain.entity.Reference
import acal.com.core.domain.entity.WaterMeter
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal

@Document("water_meter")
data class WaterMeterModel(
    val id: String,
    val reference: String,
    val linkId: String,
    val start: Double,
    val end: Double,
    val value: BigDecimal
)

fun WaterMeterModel.toDomain(): WaterMeter = WaterMeter(
    id = id,
    reference  = Reference.of(reference),
    linkId = linkId,
    start = start,
    end = end,
    value = value,
)

fun WaterMeter.toEntity(): WaterMeterModel = WaterMeterModel(
    id = id,
    reference = reference.toString(),
    linkId = linkId,
    start = start,
    end = end,
    value = value
)

