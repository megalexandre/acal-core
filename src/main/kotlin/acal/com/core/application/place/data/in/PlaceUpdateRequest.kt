package acal.com.core.application.place.data.`in`

import acal.com.core.application.address.data.`in`.AddressUpdateRequest
import acal.com.core.domain.entity.Place
import jakarta.validation.constraints.NotBlank

data class PlaceUpdateRequest(
    @field:NotBlank(message = "Id is required.")
    val id: String,

    @field:NotBlank(message = "Number is required.")
    val number: String,

    val letter: String,

    val address: AddressUpdateRequest
) {
    fun toDomain() = Place(
        id = id,
        number = number.trim(),
        letter = letter.trim(),
        address = address.toDomain()
    )
}
