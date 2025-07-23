package acal.com.core.domain.usecase.customer

import acal.com.core.domain.datasource.CustomerDataSource
import acal.com.core.domain.entity.Customer
import org.springframework.stereotype.Component

@Component
class CustomerByIdInUseCase(
    private val customerDataSource: CustomerDataSource
) {

    fun execute(ids: Collection<String>): Collection<Customer>? =
        customerDataSource.findByIdIn(ids)

}

