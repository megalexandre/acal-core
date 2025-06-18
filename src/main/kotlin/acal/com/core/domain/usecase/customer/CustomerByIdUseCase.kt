package acal.com.core.domain.usecase.customer

import acal.com.core.domain.datasource.CustomerDataSource
import acal.com.core.domain.entity.Customer
import org.springframework.stereotype.Component

@Component
class CustomerByIdUseCase(
    val customerDataSource: CustomerDataSource
) {

    fun execute(id: String): Customer? =
        customerDataSource.findById(id)

}

