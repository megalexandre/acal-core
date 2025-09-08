package acal.com.core.domain.usecase.watermeter

import acal.com.core.domain.datasource.WaterMeterDataSource
import org.springframework.stereotype.Component

@Component
class WaterMeterDeleteUseCase(
    private val dataSource: WaterMeterDataSource
) {
    fun execute(id: String) =
        dataSource.delete(id)
}
