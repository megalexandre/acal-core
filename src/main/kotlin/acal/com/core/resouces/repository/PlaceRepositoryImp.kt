package acal.com.core.resouces.repository

import acal.com.core.domain.datasource.PlaceDataSource
import acal.com.core.domain.entity.Place
import acal.com.core.domain.valueobject.PlaceFilter
import acal.com.core.infrastructure.event.EventType
import acal.com.core.infrastructure.event.PlaceEvent
import acal.com.core.resouces.PlaceModel
import acal.com.core.resouces.repository.query.PlaceQuery
import acal.com.core.resouces.toDomain
import acal.com.core.resouces.toEntity
import org.springframework.context.ApplicationEventPublisher
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
class PlaceRepositoryImp(
    private val placeRepository: PlaceRepository,
    private val mongoTemplate: MongoTemplate,
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

    override fun paginate(filter: PlaceFilter): Page<Place> {
        val query = PlaceQuery().query(filter)

        val pageable = PageRequest.of(filter.page, filter.size)

        val total = mongoTemplate.count(
            Query.of(query)
                .limit(-1).skip(-1), PlaceModel::class.java)

        query.with(pageable)

        val content = mongoTemplate.find(query, PlaceModel::class.java).map { it.toDomain() }

        return PageImpl(content, pageable, total)
    }
}

interface PlaceRepository: MongoRepository<PlaceModel, String>
