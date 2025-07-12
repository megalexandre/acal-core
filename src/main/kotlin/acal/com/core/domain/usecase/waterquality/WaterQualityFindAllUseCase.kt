package acal.com.core.domain.usecase.waterquality

import acal.com.core.domain.datasource.WaterQualityDataSource
import acal.com.core.domain.entity.WaterQuality
import org.springframework.stereotype.Component

@Component
class WaterQualityFindAllUseCase(
    private val dataSource: WaterQualityDataSource
) {
    fun execute(): Collection<WaterQuality> =
        dataSource.findAll()
}
