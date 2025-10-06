package acal.com.core.application.place.data.`in`

import acal.com.core.application.address.data.`in`.AddressRequest
import acal.com.core.domain.entity.Place
import jakarta.validation.constraints.NotBlank

data class PlaceUpdateRequest(
    @field:NotBlank(message = "Id is required.")
    val id: String,

    @field:NotBlank(message = "Number is required.")
    val number: String,

    val name: String,

    val letter: String?,

    val address: AddressRequest,

) {
    fun toDomain() = Place(
        id = id,
        name= name.trim(),
        number = number.trim(),
        letter = letter?.trim(),
        address = address.toDomain(),
    )
}
