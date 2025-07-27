package acal.com.core.domain.datasource

import acal.com.core.domain.entity.Place
import acal.com.core.domain.valueobject.PlaceFilter
import org.springframework.data.domain.Page

interface PlaceDataSource: DefaultDataSource<Place>{
    fun paginate(filter: PlaceFilter): Page<Place>
}
