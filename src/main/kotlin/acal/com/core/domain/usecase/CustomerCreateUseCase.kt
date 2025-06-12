package acal.com.core.domain.usecase

import acal.com.core.domain.datasource.CustomerDataSource
import acal.com.core.domain.entity.Customer
import org.springframework.stereotype.Component

@Component
class CustomerCreateUseCase(
    val customerDataSource: CustomerDataSource
) {

    fun execute(customer: Customer): Customer = with(customer){
        customerDataSource.findByIdentityCard(identityCard)?.let {
            throw IllegalArgumentException("Customer with identity card number ${identityCard.number} already exists")
        }.let{ customerDataSource.save(this) }
    }
}

