package acal.com.core.resources

import acal.com.core.comons.normalize
import acal.com.core.domain.entity.Customer
import acal.com.core.domain.entity.IdentityCard
import acal.com.core.domain.entity.PhoneNumber
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document("customer")
data class CustomerModel(

    @Id
    val id: String,

    @CreatedDate
    var createdAt: LocalDateTime? = null,

    @LastModifiedDate
    var updatedAt: LocalDateTime? = null,

    val name: String,
    val normalizedName: String?,
    val identityCard: String,
    val phoneNumber: String? = null,
    val partnerNumber: String? = null,
    val voter: Boolean
)

fun CustomerModel.toDomain(): Customer = Customer(
    id = id,
    name = name,
    identityCard = IdentityCard(identityCard),
    phoneNumber = phoneNumber?.let { PhoneNumber(it) },
    partnerNumber = partnerNumber,
    voter = voter
)

fun Customer.toEntity(): CustomerModel = CustomerModel(
    id = id,
    name = name,
    normalizedName = name.normalize(),
    identityCard = identityCard.raw,
    phoneNumber = phoneNumber?.raw,
    partnerNumber = partnerNumber,
    voter = voter
)
