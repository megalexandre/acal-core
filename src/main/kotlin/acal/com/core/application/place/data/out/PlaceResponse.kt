package acal.com.core.application.place.data.out

import acal.com.core.domain.entity.Place

data class PlaceResponse(
    val id: String,
    val number: String,
    val letter: String,
    val address: String,
)

fun Place.placeResponse() = PlaceResponse(
    id = id,
    number = number,
    letter = letter,
    address = address,
)

fun List<Place>.placeResponse() = this.map { it.placeResponse() }
fun Collection<Place>.placeResponse() = this.map { it.placeResponse() }
