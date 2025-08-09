package acal.com.core.resouces

import acal.com.core.domain.entity.Place
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "place")
data class PlaceModel(
    @Id
    val id: String,
    val number: String,
    val address: AddressModel,
)

fun PlaceModel.toDomain() = Place(
    id = id,
    number = number,
    address = address.toDomain(),
)

fun Place.toEntity() = PlaceModel(
    id = id,
    number = number,
    address = address.toEntity()
)
