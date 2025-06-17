package acal.com.core.application.data.`in`

import acal.com.core.comons.Id
import acal.com.core.domain.entity.Customer
import acal.com.core.domain.entity.IdentityCard
import acal.com.core.domain.entity.PhoneNumber

data class CustomerCreateRequest (
    val name: String,
    val identityCard: String,
    val phoneNumber: String? = null,
    val partnerNumber: String? = null,
    val voter: Boolean = true,
) {
    fun toDomain() = Customer(
        id = Id.random(),
        name = name.trimIndent(),
        identityCard = IdentityCard(identityCard),
        phoneNumber = phoneNumber?.let { PhoneNumber(it) },
        partnerNumber = partnerNumber,
        voter = voter
    )
}

fun Collection<CustomerCreateRequest>.toDomain() =  this.map { it.toDomain() }

