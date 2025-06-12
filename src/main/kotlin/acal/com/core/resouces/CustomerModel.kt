package acal.com.core.resouces

import acal.com.core.domain.entity.AuditInfo
import acal.com.core.domain.entity.Customer
import acal.com.core.domain.entity.IdentityCard
import acal.com.core.domain.entity.PhoneNumber
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document("customer")
class CustomerModel(

    @Id
    val id: String,
    val auditInfo: AuditInfo,
    val name: String,
    @Indexed(unique = true)
    val identityCard: String,
    val phoneNumber: String? = null,
    val partnerNumber: String? = null,
    val voter: Boolean
)

fun CustomerModel.toDomain(): Customer = Customer(
    id = id,
    auditInfo = auditInfo,
    name = name,
    identityCard = IdentityCard(identityCard),
    phoneNumber = phoneNumber?.let { PhoneNumber(it) },
    partnerNumber = partnerNumber,
    voter = voter
)

fun Customer.toEntity(): CustomerModel = CustomerModel(
    id = id,
    auditInfo = auditInfo,
    name = name,
    identityCard = identityCard.raw,
    phoneNumber = phoneNumber?.raw,
    partnerNumber = partnerNumber,
    voter = voter
)

