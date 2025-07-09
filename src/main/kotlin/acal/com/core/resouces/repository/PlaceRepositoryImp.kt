package acal.com.core.resouces.repository

import acal.com.core.domain.datasource.PlaceDataSource
import acal.com.core.domain.entity.Place
import acal.com.core.infrastructure.event.EventType
import acal.com.core.infrastructure.event.PlaceEvent
import acal.com.core.resouces.PlaceModel
import acal.com.core.resouces.toDomain
import acal.com.core.resouces.toEntity
import org.springframework.context.ApplicationEventPublisher
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
class PlaceRepositoryImp(
    private val placeRepository: PlaceRepository,
    private val publisher: ApplicationEventPublisher
): PlaceDataSource {

    override fun save(t: Place): Place =
        placeRepository.save(t.toEntity()).toDomain()

    override fun update(t: Place): Place {
        return save(t).also {
           publisher.publishEvent(PlaceEvent(eventType = EventType.UPDATE, place = it))
        }
    }

    override fun save(t: Collection<Place>): Collection<Place> {
        return placeRepository.saveAll(t.map { it.toEntity() }).map { it.toDomain() }
    }

    override fun findById(id: String): Place? =
        placeRepository.findById(id).orElse(null)?.toDomain()

    override fun deleteById(id: String) =
        placeRepository.deleteById(id)

    override fun findAll(): Collection<Place> =
        placeRepository.findAll(
            Sort.by(
                Sort.Order.asc("address"),
                Sort.Order.asc("number"),
                Sort.Order.asc("letter")
            )
        ).map { it.toDomain() }
}

interface PlaceRepository: MongoRepository<PlaceModel, String>
