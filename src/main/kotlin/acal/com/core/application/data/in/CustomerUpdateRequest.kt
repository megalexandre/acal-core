package acal.com.core.application.data.`in`

import acal.com.core.domain.entity.Customer
import acal.com.core.domain.entity.IdentityCard
import acal.com.core.domain.entity.PhoneNumber
import jakarta.validation.constraints.NotBlank

data class CustomerUpdateRequest (

    @field:NotBlank(message = "Id is required.")
    val id: String,

    @field:NotBlank(message = "Name is required.")
    val name: String,

    @field:NotBlank(message = "Identity card is required.")
    val identityCard: String,

    val phoneNumber: String? = null,

    val partnerNumber: String? = null,

    @field:NotBlank(message = "voter is required.")
    val voter: Boolean
) {
    fun toDomain() = Customer(
        id = id,
        createdAt = null,
        updatedAt = null,
        name = name,
        identityCard = IdentityCard(identityCard),
        phoneNumber = phoneNumber?.let { PhoneNumber(it) },
        partnerNumber = partnerNumber,
        voter = voter
    )
}

