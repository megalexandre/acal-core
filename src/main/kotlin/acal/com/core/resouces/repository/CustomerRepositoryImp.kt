package acal.com.core.resouces.repository

import acal.com.core.domain.datasource.AddressDataSource
import acal.com.core.domain.entity.Address
import acal.com.core.resouces.AddressModel
import acal.com.core.resouces.toDomain
import acal.com.core.resouces.toEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
class AddressRepositoryImp(
    val addressRepository: AddressRepository
): AddressDataSource {

    override fun findAll(): Collection<Address> = addressRepository.findAll()
        .sortedBy { it.name }
        .map { it.toDomain() }

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun save(address: Address): Address = addressRepository.save(address.toEntity()).toDomain()

    override fun save(t: Collection<Address>): Collection<Address> =
        addressRepository.saveAll(t.map { it.toEntity() }).map { it.toDomain() }

    override fun deleteById(id: String) {

        addressRepository.deleteById(id)
    }

    override fun findById(id: String): Address? =
        addressRepository.findById(id).orElse(null)?.toDomain()
}

interface AddressRepository: MongoRepository<AddressModel, String>