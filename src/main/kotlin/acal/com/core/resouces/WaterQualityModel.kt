package acal.com.core.resouces

import acal.com.core.domain.entity.WaterQuality
import acal.com.core.domain.entity.WaterAnalysis
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import acal.com.core.domain.entity.Reference

@Document("water_quality")
data class WaterQualityModel(
    @Id
    val id: String,
    val reference: String,
    val analysis: Collection<WaterAnalysisModel>
)
@Document("water_analysis")
data class WaterAnalysisModel(
    val name: String,
    val required: String,
    val analyzed: String,
    val conformity: String,
)

fun WaterAnalysisModel.toDomain(): WaterAnalysis = WaterAnalysis(
    name = name,
    required = required,
    analyzed = analyzed,
    conformity = conformity
)

fun WaterAnalysis.toEntity(): WaterAnalysisModel = WaterAnalysisModel(
    name = name,
    required = required,
    analyzed = analyzed,
    conformity = conformity
)

fun WaterQualityModel.toDomain(): WaterQuality = WaterQuality(
    id = id,
    reference = Reference.of( reference),
    analysis = analysis.map { it.toDomain() }
)

fun WaterQuality.toEntity(): WaterQualityModel = WaterQualityModel(
    id = id,
    reference = reference.toString(),
    analysis = analysis.map { it.toEntity() }
)