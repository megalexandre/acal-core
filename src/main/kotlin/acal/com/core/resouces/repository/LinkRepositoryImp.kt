package acal.com.core.resouces.repository

import acal.com.core.domain.datasource.LinkDataSource
import acal.com.core.domain.entity.Link
import acal.com.core.domain.entity.Place
import acal.com.core.domain.entity.Reference
import acal.com.core.domain.valueobject.LinkFilter
import acal.com.core.infrastructure.event.CategoryEvent
import acal.com.core.infrastructure.event.CustomerEvent
import acal.com.core.infrastructure.event.EventType.UPDATE
import acal.com.core.infrastructure.event.PlaceEvent
import acal.com.core.resouces.LinkModel
import acal.com.core.resouces.repository.query.LinkQuery
import acal.com.core.resouces.toDomain
import acal.com.core.resouces.toEntity
import org.springframework.context.event.EventListener
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import org.springframework.data.mongodb.repository.Query as QueryAnnotation

@Repository
class LinkRepositoryImp(
    private val linkRepository: LinkRepository,
    private val mongoTemplate: MongoTemplate,
): LinkDataSource {

    override fun paginate(filter: LinkFilter): Page<Link> {

        val query = LinkQuery().query(filter)

        val pageable = PageRequest.of(filter.page, filter.size)

        val total = mongoTemplate.count(
            Query.of(query)
            .limit(-1).skip(-1), LinkModel::class.java)

        query.with(pageable)

        val content = mongoTemplate.find(query, LinkModel::class.java).map { it.toDomain() }

        return PageImpl(content, pageable, total)
    }

    override fun findActiveLinksWithoutReference(reference: Reference): Collection<Link> =
        linkRepository.findActiveLinksWithoutReference(reference.toString()).map { it.toDomain() }

    override fun findActiveLinkByPlace(place: Place): Link? =
        linkRepository.findByPlaceIdAndActiveTrue(place.id)?.toDomain()

    override fun save(t: Collection<Link>): Collection<Link> {
        return linkRepository.saveAll(t.map { it.toEntity() }).map { it.toDomain() }
    }

    override fun save(t: Link): Link =
        linkRepository.save(t.toEntity()).toDomain()

    override fun deleteById(id: String) {
        findById(id)?.let {
            save(it.copy(active = false))
        }
    }

    override fun findById(id: String): Link? =
        linkRepository.findById(id).orElse(null)
            ?.toDomain()


    @EventListener
    fun categoryListener(event: CategoryEvent) {
        if(event.eventType == UPDATE) {
            linkRepository.findByCategoryId(event.category.id)
                .forEach { link ->
                    linkRepository.save(
                        link.copy(category = event.category.toEntity())
                    )
                }
        }
    }

    @EventListener
    fun placeListener(event: PlaceEvent) {
        if(event.eventType == UPDATE) {
            linkRepository.findByPlaceId(event.place.id)
                .forEach { link ->
                    linkRepository.save(
                        link.copy(place = event.place.toEntity())
                    )
                }
        }
    }

    @EventListener
    fun customerListener(event: CustomerEvent) {
        if(event.eventType == UPDATE) {
            linkRepository.findByCustomerId(event.customer.id)
                .forEach { link ->
                    linkRepository.save(
                        link.copy(customer = event.customer.toEntity())
                    )
                }
        }
    }
}

interface LinkRepository: MongoRepository<LinkModel, String>{
    fun findByCategoryId(categoryId: String): Collection<LinkModel>
    fun findByPlaceId(placeId: String): Collection<LinkModel>
    fun findByCustomerId(customerId: String): Collection<LinkModel>

    fun findByPlaceIdAndActiveTrue(placeId: String): LinkModel?

    @QueryAnnotation("{ 'active': true, 'references': { \$not: { \$in: [?0] } } }")
    fun findActiveLinksWithoutReference(reference: String): Collection<LinkModel>
}
