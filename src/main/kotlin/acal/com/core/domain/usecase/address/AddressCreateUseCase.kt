package acal.com.core.domain.usecase.address

import acal.com.core.domain.datasource.AddressDataSource
import acal.com.core.domain.entity.Address
import org.springframework.stereotype.Component

@Component
class AddressCreateUseCase(
    val addressDataSource: AddressDataSource,
) {

    fun execute(address: Address): Address =
        addressDataSource.save(address)

}
