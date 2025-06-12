package acal.com.core.application.data.`in`

import acal.com.core.comons.Id
import acal.com.core.domain.entity.AuditInfo
import acal.com.core.domain.entity.Customer
import acal.com.core.domain.entity.IdentityCard
import acal.com.core.domain.entity.PhoneNumber
import java.time.LocalDateTime

data class CustomerCreateRequest (
    val name: String,
    val identityCard: String,
    val phoneNumber: String? = null,
    val partnerNumber: String? = null,
    val voter: Boolean
) {
    fun toDomain() = Customer(
        id = Id.random(),
        auditInfo = AuditInfo(
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        ),
        name = name,
        identityCard = IdentityCard(identityCard),
        phoneNumber = phoneNumber?.let { PhoneNumber(it) },
        partnerNumber = partnerNumber,
        voter = voter
    )
}

