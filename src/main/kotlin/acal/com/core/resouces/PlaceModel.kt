package acal.com.core.resouces

import acal.com.core.domain.entity.Place
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.CompoundIndex
import org.springframework.data.mongodb.core.index.CompoundIndexes
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "places")
data class PlaceModel(
    @Id
    val id: String,
    val number: String,
    val letter: String,
    val address: AddressModel
)

fun PlaceModel.toDomain() = Place(
    id = id,
    number = number,
    letter = letter,
    address = address.toDomain()
)

fun Place.toEntity() = PlaceModel(
    id = id,
    number = number,
    letter = letter,
    address = address.toEntity()
)
