package acal.com.core.domain.usecase.address

import acal.com.core.domain.datasource.AddressDataSource
import acal.com.core.domain.entity.Address
import org.springframework.stereotype.Component

@Component
class AddressByIdUseCase(
    val addressDataSource: AddressDataSource
) {

    fun execute(id: String): Address? =
        addressDataSource.findById(id)

}
