package acal.com.core.resources

import acal.com.core.domain.entity.Reference
import acal.com.core.domain.entity.WaterMeter
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal

@Document("water_meter")
data class WaterMeterModel(
    val id: String,
    val reference: String,
    val linkId: String,
    val linkName: String,
    val start: Double,
    val end: Double,
    val pricePerUnit: BigDecimal,
    val freeTier: Double,
)

fun WaterMeterModel.toDomain(): WaterMeter = WaterMeter(
    id = id,
    reference  = Reference.of(reference),
    linkId = linkId,
    linkName = linkName,
    start = start,
    end = end,
    pricePerUnit = pricePerUnit,
    freeTier = freeTier
)

fun WaterMeter.toEntity(): WaterMeterModel = WaterMeterModel(
    id = id,
    reference = reference.toString(),
    linkId = linkId,
    linkName = linkName,
    start = start,
    end = end,
    pricePerUnit = pricePerUnit,
    freeTier = freeTier
)

