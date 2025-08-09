package acal.com.core.application.address.data.`in`

import acal.com.core.domain.entity.Address

data class AddressRequest (
    val id: String,
    val name: String
) {
    fun toDomain() = Address(
        id = id,
        name = name.trimIndent()
    )
}

fun Collection<AddressRequest>.toDomain(): Collection<Address> = map { it.toDomain() }
