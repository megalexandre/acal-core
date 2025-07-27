package acal.com.core.domain.usecase.customer

import acal.com.core.domain.datasource.CustomerDataSource
import acal.com.core.domain.entity.Customer
import acal.com.core.domain.valueobject.CustomerFilter
import org.springframework.data.domain.Page
import org.springframework.stereotype.Component

@Component
class CustomerPaginateUseCase(
    private val dataSource: CustomerDataSource
) {
    fun execute(filter: CustomerFilter): Page<Customer> =
        dataSource.paginate(filter)
}