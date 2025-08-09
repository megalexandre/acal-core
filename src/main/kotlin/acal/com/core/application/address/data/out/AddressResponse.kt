package acal.com.core.application.address.data.out

import acal.com.core.domain.entity.Address

data class AddressResponse(
    val id: String,
    val name: String
)

fun Address.response() = AddressResponse(
    id = id,
    name = name
)

fun Collection<Address>.response(): Collection<AddressResponse> = map { it.response() }
