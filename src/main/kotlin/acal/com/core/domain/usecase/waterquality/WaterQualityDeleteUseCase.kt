package acal.com.core.domain.usecase.waterquality

import acal.com.core.domain.datasource.WaterQualityDataSource
import org.springframework.stereotype.Component

@Component
class WaterQualityDeleteUseCase(
    private val dataSource: WaterQualityDataSource
) {
    fun execute(id: String) =
        dataSource.deleteById(id)
}
