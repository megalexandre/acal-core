package acal.com.core.domain.usecase.address

import acal.com.core.domain.datasource.AddressDataSource
import org.springframework.stereotype.Component

@Component
class AddressDeleteUseCase(
    val addressDataSource: AddressDataSource
) {

    fun execute(id: String){
        addressDataSource.deleteById(id)
    }

}
