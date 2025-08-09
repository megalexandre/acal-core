package acal.com.core.application.place.data.`in`

import acal.com.core.application.address.data.`in`.AddressRequest
import acal.com.core.domain.entity.Place
import jakarta.validation.constraints.NotBlank

data class PlaceRequest(
    @field:NotBlank(message = "Id is required.")
    val id: String,

    @field:NotBlank(message = "Number is required.")
    val number: String,

    val letter: String,

    val address: AddressRequest

) {
    fun toDomain() = Place(
        id = id,
        number = number.trim(),
        address = address.toDomain()
    )
}
