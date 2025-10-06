package acal.com.core.resources

import acal.com.core.domain.entity.Place
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "place")
data class PlaceModel(

    @Id
    val id: String,
    val name: String?,
    val number: String,
    val letter: String?,
    val address: AddressModel,
)

fun PlaceModel.toDomain() = Place(
    id = id,
    number = number,
    name = name,
    letter  = letter,
    address = address.toDomain(),
)

fun Place.toEntity() = PlaceModel(
    id = id,
    number = number,
    letter  = letter,
    name = name,
    address = address.toEntity()
)
