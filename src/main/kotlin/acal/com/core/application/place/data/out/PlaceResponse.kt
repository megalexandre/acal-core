package acal.com.core.application.place.data.out

import acal.com.core.application.address.data.out.AddressResponse
import acal.com.core.application.address.data.out.response
import acal.com.core.domain.entity.Place
import org.springframework.data.domain.Page

data class PlaceResponse(
    val id: String,
    val name: String?,
    val fullName: String,
    val number: String,
    val letter: String?,
    val address: AddressResponse
)

fun Place.placeResponse() = PlaceResponse(
    id = id,
    name = name,
    fullName = fullName,
    number = number,
    letter = letter,
    address = address.response(),
)

fun List<Place>.placeResponse() = this.map { it.placeResponse() }
fun Collection<Place>.placeResponse() = this.map { it.placeResponse() }
fun Page<Place>.response() = this.map { it.placeResponse() }