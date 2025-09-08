package acal.com.core.domain.usecase.watermeter

import acal.com.core.domain.datasource.WaterMeterDataSource
import acal.com.core.domain.entity.WaterMeter
import org.springframework.stereotype.Component

@Component
class WaterMeterCreateUseCase(
    private val dataSource: WaterMeterDataSource
) {
    fun execute(waterMeter: WaterMeter): WaterMeter =
        dataSource.save(waterMeter)
}
