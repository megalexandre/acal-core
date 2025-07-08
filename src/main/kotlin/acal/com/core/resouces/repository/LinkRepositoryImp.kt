package acal.com.core.resouces.repository

import acal.com.core.domain.datasource.LinkDataSource
import acal.com.core.domain.entity.Link
import acal.com.core.resouces.LinkModel
import acal.com.core.resouces.toDomain
import acal.com.core.resouces.toEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
class LinkRepositoryImp(
    private val linkRepository: LinkRepository,
    private val categoryRepository: CategoryRepository,
    private val placeRepository: PlaceRepository,
    private val customerRepository: CustomerRepository
): LinkDataSource {

    override fun save(link: Link): Link =
        linkRepository.save(link.toEntity()).toDomain(
            customer = link.customer,
            place = link.place,
            category = link.category
        )

    override fun findById(id: String): Link? {

        val linkModel = linkRepository.findById(id).orElse(null) ?: return null

        val customer = customerRepository.findById(linkModel.customerId)
            .map { it.toDomain() }
            .orElse(null) ?: return null

        val category = categoryRepository.findById(linkModel.categoryId)
            .map { it.toDomain() }
            .orElse(null) ?: return null

        val place = placeRepository.findById(linkModel.placeId)
            .map { it.toDomain() }
            .orElse(null) ?: return null

        return linkModel.toDomain(
            customer = customer,
            category = category,
            place = place
        )
    }

}

interface LinkRepository: MongoRepository<LinkModel, String>

