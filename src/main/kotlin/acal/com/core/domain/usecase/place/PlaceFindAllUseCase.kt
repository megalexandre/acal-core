package acal.com.core.domain.usecase.place

import acal.com.core.domain.datasource.PlaceDataSource
import acal.com.core.domain.entity.Place
import org.springframework.stereotype.Component

@Component
class PlaceFindAllUseCase(
    val dataSource: PlaceDataSource
) {

    fun execute(): Collection<Place> =
        dataSource.findAll()

}
