package acal.com.core.resources.repository

import acal.com.core.domain.datasource.CustomerDataSource
import acal.com.core.domain.entity.Customer
import acal.com.core.domain.entity.IdentityCard
import acal.com.core.domain.valueobject.CustomerFilter
import acal.com.core.infrastructure.event.CustomerEvent
import acal.com.core.infrastructure.event.EventType
import acal.com.core.resources.CustomerModel
import acal.com.core.resources.repository.query.CustomerQuery
import acal.com.core.resources.toDomain
import acal.com.core.resources.toEntity
import org.springframework.context.ApplicationEventPublisher
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
class CustomerRepositoryImp(
    private val repository: CustomerRepository,
    private val publisher: ApplicationEventPublisher,
    private val mongoTemplate: MongoTemplate
): CustomerDataSource {

    override fun findByIdentityCard(identityCard: IdentityCard): Customer? =
        repository.findByIdentityCard(identityCard.raw)?.firstOrNull()?.toDomain()

    override fun findAll(): Collection<Customer> = repository.findAll().map { it.toDomain() }

    override fun save(t: Customer): Customer = repository.save(t.toEntity()).toDomain()

    override fun update(t: Customer): Customer {
        return save(t).also {
            publisher.publishEvent(CustomerEvent(eventType = EventType.UPDATE, customer = it))
        }
    }

    override fun save(t: Collection<Customer>): Collection<Customer> =
        repository.saveAll(t.map { it.toEntity() }).map { it.toDomain() }

    override fun deleteById(id: String) {
        repository.deleteById(id)
    }

    override fun findById(id: String): Customer? =
        repository.findById(id).orElse(null)?.toDomain()

    override fun findByIdIn(id: Collection<String>): Collection<Customer> =
        repository.findByIdIn(id).map { it.toDomain() }

    override fun paginate(filter: CustomerFilter): Page<Customer> {
        val query = CustomerQuery().query(filter)

        val pageable = PageRequest.of(filter.page, filter.size)

        val total = mongoTemplate.count(
            Query.of(query)
                .limit(-1).skip(-1), CustomerModel::class.java)

        query.with(pageable)

        val content = mongoTemplate.find(query, CustomerModel::class.java).map { it.toDomain() }

        return PageImpl(content, pageable, total)
    }
}

interface CustomerRepository: MongoRepository<CustomerModel, String>{
    fun findByIdentityCard(identityCard: String): Collection<CustomerModel>?
    fun findByIdIn(ids: Collection<String>): Collection<CustomerModel>
}