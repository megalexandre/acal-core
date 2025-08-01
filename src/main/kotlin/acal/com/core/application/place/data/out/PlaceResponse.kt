package acal.com.core.application.place.data.out

import acal.com.core.domain.entity.Place
import org.springframework.data.domain.Page

data class PlaceResponse(
    val id: String,
    val number: String,
    val letter: String,
    val name: String,
)

fun Place.placeResponse() = PlaceResponse(
    id = id,
    number = number,
    letter = letter,
    name = name,
)

fun List<Place>.placeResponse() = this.map { it.placeResponse() }
fun Collection<Place>.placeResponse() = this.map { it.placeResponse() }
fun Page<Place>.response() = this.map { it.placeResponse() }