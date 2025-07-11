package acal.com.core.domain.usecase.waterquality

import acal.com.core.domain.datasource.WaterQualityDataSource
import acal.com.core.domain.entity.WaterQuality
import org.springframework.stereotype.Component

@Component
class WaterQualityCreateUseCase(
    private val dataSource: WaterQualityDataSource
) {
    fun execute(waterQuality: WaterQuality): WaterQuality =
        dataSource.save(waterQuality)
}
