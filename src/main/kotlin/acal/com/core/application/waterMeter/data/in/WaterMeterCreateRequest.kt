package acal.com.core.application.waterMeter.data.`in`

import acal.com.core.comons.Id
import acal.com.core.domain.entity.Reference
import acal.com.core.domain.entity.WaterMeter
import jakarta.validation.constraints.NotBlank
import java.math.BigDecimal

data class WaterMeterCreateRequest(

    val id: String?,

    @field:NotBlank(message = "Reference is required.")
    val reference: Reference,

    @field:NotBlank(message = "Link is required.")
    val linkId: String,

    @field:NotBlank(message = "Link is required.")
    val linkName: String,

    @field:NotBlank(message = "start is required.")
    val start: Double,

    @field:NotBlank(message = "end is required.")
    val end: Double,

    @field:NotBlank(message = "pricePerUnit is required.")
    val pricePerUnit: BigDecimal,

    @field:NotBlank(message = "freeTier is required.")
    val freeTier: Double
) {
    fun toDomain() = WaterMeter(
        id = id ?: Id.random(),
        reference = reference,
        linkId = linkId,
        linkName = linkName,
        start = start,
        end = end,
        pricePerUnit = pricePerUnit,
        freeTier = freeTier
    )
}
