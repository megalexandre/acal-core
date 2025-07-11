package acal.com.core.domain.entity

class WaterQuality(
    val id: String,
    val reference: String,
    val analysis: Collection<WaterAnalysis>
)

class WaterAnalysis(
    val name: String,
    val required: String,
    val analyzed: String,
    val conformity: String,
)

