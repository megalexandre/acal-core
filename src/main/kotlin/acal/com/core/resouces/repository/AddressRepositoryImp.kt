package acal.com.core.resouces.repository

import acal.com.core.domain.datasource.AddressDataSource
import acal.com.core.domain.entity.Address
import acal.com.core.resouces.AddressModel
import acal.com.core.resouces.toDomain
import acal.com.core.resouces.toEntity
import org.springframework.dao.DuplicateKeyException
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
class AddressRepositoryImp(
    private val addressRepository: AddressRepository
): AddressDataSource {

    override fun findAll(): Collection<Address> =
        addressRepository.findAll(
            Sort.by(
                Sort.Order.asc("name"),
            )
        ).map { it.toDomain() }

    override fun save(t: Address): Address =
        addressRepository.save(t.toEntity()).toDomain()

    override fun update(t: Address): Address {
       return save(t)
    }

    override fun save(t: Collection<Address>): Collection<Address> =
        addressRepository.saveAll(t.map { it.toEntity() }).map { it.toDomain() }

    override fun deleteById(id: String) {
        addressRepository.deleteById(id)
    }

    override fun findById(id: String): Address? =
        addressRepository.findById(id).orElse(null)?.toDomain()
}

interface AddressRepository: MongoRepository<AddressModel, String>