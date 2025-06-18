package acal.com.core.domain.usecase.address

import acal.com.core.domain.datasource.AddressDataSource
import acal.com.core.domain.entity.Address
import org.springframework.stereotype.Component

@Component
class AddressCreateAllUseCase(
    val addressDataSource: AddressDataSource
) {

    fun execute(addresses: Collection<Address>): Collection<Address> =
        addressDataSource.save(addresses)

}
