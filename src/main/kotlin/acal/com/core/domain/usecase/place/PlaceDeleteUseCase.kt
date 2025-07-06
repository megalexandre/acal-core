package acal.com.core.domain.usecase.place

import acal.com.core.domain.datasource.PlaceDataSource
import org.springframework.stereotype.Component

@Component
class PlaceDeleteUseCase(
    val dataSource: PlaceDataSource
) {

    fun execute(id: String){
        dataSource.deleteById(id)
    }

}
