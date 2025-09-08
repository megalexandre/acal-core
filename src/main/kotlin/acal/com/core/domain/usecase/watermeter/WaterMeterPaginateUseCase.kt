package acal.com.core.domain.usecase.watermeter

import acal.com.core.domain.datasource.WaterMeterDataSource
import acal.com.core.domain.entity.WaterMeter
import acal.com.core.domain.valueobject.WaterMeterFilter
import org.springframework.data.domain.Page
import org.springframework.stereotype.Component

@Component
class WaterMeterPaginateUseCase(
    private val dataSource: WaterMeterDataSource,
) {
    fun execute(filter: WaterMeterFilter): Page<WaterMeter> {
        return dataSource.paginate(filter)
    }

}
