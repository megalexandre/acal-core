package acal.com.core.resouces.repository

import acal.com.core.domain.datasource.LinkDataSource
import acal.com.core.domain.entity.Link
import acal.com.core.infrastructure.event.CategoryEvent
import acal.com.core.infrastructure.event.CustomerEvent
import acal.com.core.infrastructure.event.EventType.UPDATE
import acal.com.core.infrastructure.event.PlaceEvent
import acal.com.core.resouces.LinkModel
import acal.com.core.resouces.toDomain
import acal.com.core.resouces.toEntity
import org.springframework.context.event.EventListener
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
class LinkRepositoryImp(
    private val linkRepository: LinkRepository,
): LinkDataSource {

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

}

