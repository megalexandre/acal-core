package acal.com.core.domain.entity

data class WaterQuality(
    val id: String,
    val reference: Reference,
    val analysis: Collection<WaterAnalysis>
)

data class WaterAnalysis(
    val name: String,
    val required: String,
    val analyzed: String,
    val conformity: String,
)

