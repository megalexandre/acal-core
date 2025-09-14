package acal.com.core.application.waterMeter.data.`in`

import acal.com.core.domain.entity.Reference
import acal.com.core.domain.entity.WaterMeterPreview
import org.springframework.data.domain.Page

class WaterMeterPreviewResponse (
    val id: String,
    val reference: Reference,
    val linkId: String,
    val linkName: String,
    val start: Double,
    val end: Double,
    val pricePerUnit: Double,
    val freeTier: Double,
)

fun WaterMeterPreview.response(): WaterMeterPreviewResponse = WaterMeterPreviewResponse(
    id = id,
    reference = reference,
    linkId = linkId,
    linkName = linkName,
    start = start,
    end = end,
    pricePerUnit = pricePerUnit.toDouble(),
    freeTier = freeTier
)

fun Collection<WaterMeterPreview>.response(): Collection<WaterMeterPreviewResponse> = this.map { it.response() }
fun Page<WaterMeterPreview>.response(): Page<WaterMeterPreviewResponse> = this.map { it.response() }
