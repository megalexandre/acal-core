package acal.com.core.resouces.repository

import acal.com.core.domain.datasource.CustomerDataSource
import acal.com.core.domain.entity.Customer
import acal.com.core.domain.entity.IdentityCard
import acal.com.core.resouces.CustomerModel
import acal.com.core.resouces.toDomain
import acal.com.core.resouces.toEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
class CustomerRepositoryImp(
    val customerRepository: CustomerRepository
): CustomerDataSource {

    override fun findByIdentityCard(identityCard: IdentityCard): Customer? =
        customerRepository.findByIdentityCard(identityCard.raw)?.firstOrNull()?.toDomain()

    override fun findAll(): Collection<Customer> = customerRepository.findAll().map { it.toDomain() }

    override fun save(t: Customer): Customer = customerRepository.save(t.toEntity()).toDomain()

    override fun save(t: Collection<Customer>): Collection<Customer> =
        customerRepository.saveAll(t.map { it.toEntity() }).map { it.toDomain() }

    override fun deleteById(id: String) {
        customerRepository.deleteById(id)
    }

    override fun findById(id: String): Customer? =
        customerRepository.findById(id).orElse(null)?.toDomain()
}

interface CustomerRepository: MongoRepository<CustomerModel, String>{
    fun findByIdentityCard(identityCard: String): Collection<CustomerModel>?
}