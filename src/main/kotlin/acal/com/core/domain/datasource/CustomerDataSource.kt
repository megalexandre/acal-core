package acal.com.core.domain.datasource

import acal.com.core.domain.entity.Customer
import acal.com.core.domain.entity.IdentityCard


interface CustomerDataSource : DefaultDataSource<Customer>{

    fun findByIdentityCard(identityCard: IdentityCard): Customer?
    fun findByIdIn(id: Collection<String>): Collection<Customer>?
}