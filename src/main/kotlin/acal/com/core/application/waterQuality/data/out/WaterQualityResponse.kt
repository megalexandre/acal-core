package acal.com.core.application.waterQuality.data.out

import acal.com.core.domain.entity.WaterAnalysis
import acal.com.core.domain.entity.WaterQuality
import kotlin.String

data class WaterQualityResponse(
    val id: String,
    val reference: String,
    val analysis: Collection<WaterAnalysisResponse>
)

data class WaterAnalysisResponse(
    val name: String,
    val required: String,
    val analyzed: String,
    val conformity: String,
)

fun WaterQuality.response() = WaterQualityResponse(
    id = id,
    reference = reference,
    analysis = analysis.map { it.response() }
)

fun WaterAnalysis.response() = WaterAnalysisResponse(
    name = name,
    required = required,
    analyzed = analyzed,
    conformity = conformity
)

