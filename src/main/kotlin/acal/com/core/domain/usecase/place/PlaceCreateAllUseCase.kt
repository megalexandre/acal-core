package acal.com.core.domain.usecase.place

import acal.com.core.domain.datasource.PlaceDataSource
import acal.com.core.domain.entity.Place
import org.springframework.stereotype.Component

@Component
class PlaceCreateAllUseCase(
    val placeDataSource: PlaceDataSource
) {
    fun execute(places: Collection<Place>): Collection<Place> =
        placeDataSource.saveAll(places)
}
