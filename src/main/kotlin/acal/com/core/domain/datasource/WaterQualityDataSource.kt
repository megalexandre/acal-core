package acal.com.core.domain.datasource

import acal.com.core.domain.entity.Reference
import acal.com.core.domain.entity.WaterQuality

interface WaterQualityDataSource: DefaultDataSource<WaterQuality>{
    fun findByReferences(references: Collection<Reference>): Collection<WaterQuality>
}
