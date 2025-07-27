package acal.com.core.domain.usecase.place

import acal.com.core.domain.datasource.PlaceDataSource
import acal.com.core.domain.entity.Place
import acal.com.core.domain.valueobject.PlaceFilter
import org.springframework.data.domain.Page
import org.springframework.stereotype.Component

@Component
class PlacePaginateUseCase(
    private val dataSource: PlaceDataSource
) {
    fun execute(filter: PlaceFilter): Page<Place> =
        dataSource.paginate(filter)
}
