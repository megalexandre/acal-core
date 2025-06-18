package acal.com.core.resouces.repository

import acal.com.core.domain.datasource.PlaceDataSource
import acal.com.core.domain.entity.Place
import acal.com.core.resouces.PlaceModel
import acal.com.core.resouces.toDomain
import acal.com.core.resouces.toEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
class PlaceRepositoryImp(
    val placeRepository: PlaceRepository
): PlaceDataSource {

    override fun save(place: Place): Place =
        placeRepository.save(place.toEntity()).toDomain()

    override fun saveAll(places: Collection<Place>): Collection<Place> =
        placeRepository.saveAll(places.map { it.toEntity() }).map { it.toDomain() }

    override fun findById(id: String): Place? =
        placeRepository.findById(id).orElse(null)?.toDomain()

    override fun deleteById(id: String) =
        placeRepository.deleteById(id)
}

interface PlaceRepository: MongoRepository<PlaceModel, String>
