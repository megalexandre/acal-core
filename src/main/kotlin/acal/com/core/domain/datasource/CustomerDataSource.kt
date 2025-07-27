package acal.com.core.domain.datasource

import acal.com.core.domain.entity.Customer
import acal.com.core.domain.entity.IdentityCard
import acal.com.core.domain.valueobject.CustomerFilter
import org.springframework.data.domain.Page


interface CustomerDataSource : DefaultDataSource<Customer>{

    fun findByIdentityCard(identityCard: IdentityCard): Customer?
    fun findByIdIn(id: Collection<String>): Collection<Customer>?
    fun paginate(filter: CustomerFilter): Page<Customer>
}