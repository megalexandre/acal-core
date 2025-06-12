import acal.com.core.domain.entity.Customer

data class CustomerResponse(
    val id: String,
    val name: String,
    val identityCard: String,
    val phoneNumber: String?,
    val partnerNumber: String?,
    val voter: Boolean
)

fun Customer.customerResponse() = CustomerResponse(
    id = this.id,
    name = this.name,
    identityCard = this.identityCard.number,
    phoneNumber = this.phoneNumber?.number,
    partnerNumber = this.partnerNumber,
    voter = this.voter
)

fun List<Customer>.customerResponse() = this.map { it.customerResponse() }
