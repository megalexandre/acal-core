package acal.com.core.domain.usecase.customer

import acal.com.core.domain.datasource.CustomerDataSource
import org.springframework.stereotype.Component

@Component
class CustomerDeleteUseCase(
    private val customerDataSource: CustomerDataSource
) {

    fun execute(id: String) =
        customerDataSource.deleteById(id)

}

