package acal.com.core.application.place.data.`in`

import acal.com.core.application.address.data.`in`.AddressRequest
import acal.com.core.domain.entity.Place
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class PlaceRequest(
    @field:NotBlank(message = "Id is required.")
    val id: String,

    @field:NotBlank(message = "Number is required.")
    val number: String,

    val name: String?,

    @field:NotBlank(message = "letter is required.")
    val letter: String?,

    @field:NotNull
    val address: AddressRequest

) {
    fun toDomain() = Place(
        id = id,
        name = name?.trim(),
        number = number.trim(),
        letter = letter?.trim(),
        address = address.toDomain()
    )
}
