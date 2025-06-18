package acal.com.core.application.address.data.`in`

import acal.com.core.domain.entity.Address

data class AddressUpdateRequest (
    val id: String,
    val name: String
) {
    fun toDomain() = Address(
        id = id,
        name = name.trimIndent()
    )
}
