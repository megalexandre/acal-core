package acal.com.core.application.place.data.out

import acal.com.core.application.address.data.out.AddressResponse
import acal.com.core.application.address.data.out.addressResponse
import acal.com.core.domain.entity.Place
import java.time.LocalDateTime

data class PlaceResponse(
    val id: String,
    val number: String,
    val letter: String,
    val address: AddressResponse,
)

fun Place.placeResponse() = PlaceResponse(
    id = id,
    number = number,
    letter = letter,
    address = address.addressResponse()
)

fun List<Place>.placeResponse() = this.map { it.placeResponse() }
fun Collection<Place>.placeResponse() = this.map { it.placeResponse() }
