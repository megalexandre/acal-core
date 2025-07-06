package acal.com.core.domain.datasource

import acal.com.core.domain.entity.Place

interface PlaceDataSource {
    fun save(place: Place): Place
    fun saveAll(places: Collection<Place>): Collection<Place>
    fun findById(id: String): Place?
    fun deleteById(id: String)
    fun findAll(): Collection<Place>
}
