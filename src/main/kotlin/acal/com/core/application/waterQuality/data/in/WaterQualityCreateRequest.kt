package acal.com.core.application.waterQuality.data.`in`

import acal.com.core.comons.Id
import acal.com.core.domain.entity.WaterAnalysis
import acal.com.core.domain.entity.WaterQuality
import acal.com.core.domain.entity.Reference

data class WaterQualityRequest(
    val reference: String,
    val analysis: Collection<WaterAnalysisRequest>
) {
    fun toDomain() = WaterQuality(
        id = Id.random(),
        reference = Reference.of(reference.trim()),
        analysis = analysis.map { it.toDomain() }
    )
}

data class WaterAnalysisRequest(
    val name: String,
    val required: String,
    val analyzed: String,
    val conformity: String,
){
    fun toDomain() = WaterAnalysis(
        name = name.trim(),
        required = required.trim(),
        analyzed = analyzed.trim(),
        conformity = conformity.trim()
    )
}
