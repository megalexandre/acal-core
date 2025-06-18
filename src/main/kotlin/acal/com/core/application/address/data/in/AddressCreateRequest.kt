package acal.com.core.application.address.data.`in`

import acal.com.core.comons.Id
import acal.com.core.domain.entity.Address

data class AddressCreateRequest (
    val name: String
) {
    fun toDomain() = Address(
        id = Id.random(),
        name = name.trimIndent()
    )
}

fun Collection<AddressCreateRequest>.toDomain(): Collection<Address> = map { it.toDomain() }
