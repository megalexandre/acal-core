package acal.com.core.resouces

import acal.com.core.domain.entity.Link
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("link")
data class LinkModel(

    @Id
    val id: String,
    val number: String,

    val exclusiveMember: Boolean,

    val customer: CustomerModel,
    val place: PlaceModel,
    val category: CategoryModel,


    val active: Boolean,
)

fun LinkModel.toDomain(): Link = Link(
    id = id,
    number = number,
    customer = customer.toDomain(),
    place = place.toDomain(),
    category = category.toDomain(),
    exclusiveMember = exclusiveMember,
    active = active
)

fun Link.toEntity(): LinkModel = LinkModel(
    id = id,
    number = number,
    customer = customer.toEntity(),
    place = place.toEntity(),
    category = category.toEntity(),
    exclusiveMember = exclusiveMember,
    active = active
)
