package acal.com.core.domain.usecase.customer

import acal.com.core.domain.datasource.CustomerDataSource
import acal.com.core.domain.entity.Customer
import org.springframework.stereotype.Component

@Component
class CustomerCreateUseCase(
    private val customerDataSource: CustomerDataSource
) {

    fun execute(customer: Customer): Customer =
        customerDataSource.save(customer)

}

