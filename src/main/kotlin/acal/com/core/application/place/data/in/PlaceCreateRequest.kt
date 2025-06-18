package acal.com.core.application.place.data.`in`

import acal.com.core.comons.Id
import acal.com.core.domain.entity.Address
import acal.com.core.domain.entity.Place

data class PlaceCreateRequest(
    val number: String,
    val letter: String,
    val address: Address
) {
    fun toDomain() = Place(
        id = Id.random(),
        number = number.trim(),
        letter = letter.trim(),
        address = address
    )
}

fun Collection<PlaceCreateRequest>.toDomain() = this.map { it.toDomain() }
