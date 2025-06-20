import acal.com.core.domain.entity.Customer
import java.time.LocalDateTime

data class CustomerResponse(
    val id: String,
    val name: String,
    val identityCard: String,
    val phoneNumber: String?,
    val partnerNumber: String?,
    val voter: Boolean
)

fun Customer.customerResponse() = CustomerResponse(
    id = id,
    name = name,
    identityCard = identityCard.number,
    phoneNumber = phoneNumber?.number,
    partnerNumber = partnerNumber,
    voter = voter
)

fun List<Customer>.customerResponse() = this.map { it.customerResponse() }
fun Collection<Customer>.customerResponse() =  this.map { it.customerResponse() }