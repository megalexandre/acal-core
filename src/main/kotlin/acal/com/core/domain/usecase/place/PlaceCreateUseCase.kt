package acal.com.core.domain.usecase.place

import acal.com.core.domain.datasource.PlaceDataSource
import acal.com.core.domain.entity.Place
import org.springframework.stereotype.Component

@Component
class PlaceCreateUseCase(
    val placeDataSource: PlaceDataSource
) {
    fun execute(place: Place): Place =
        placeDataSource.save(place)
}
