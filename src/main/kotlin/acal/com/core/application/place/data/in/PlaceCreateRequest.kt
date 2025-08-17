package acal.com.core.application.place.data.`in`

import acal.com.core.application.address.data.`in`.AddressRequest
import acal.com.core.comons.Id
import acal.com.core.domain.entity.Place
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty

data class PlaceCreateRequest(
    val id: String?,

    @field:NotEmpty
    @field:NotBlank(message = "Number is required.")
    val number: String,

    @field:NotEmpty
    @field:NotBlank(message = "Number is required.")
    val letter: String,

    @field:NotEmpty
    @field:NotBlank(message = "Address is required.")
    val address: AddressRequest,

    ) {
    fun toDomain() = Place(
        id = id ?: Id.random(),
        letter = letter.trim(),
        number = number.trim(),
        address = address.toDomain(),
    )
}

fun Collection<PlaceCreateRequest>.toDomain() = this.map { it.toDomain() }
