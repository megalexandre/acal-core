package acal.com.core.resouces

import acal.com.core.domain.entity.Category
import acal.com.core.domain.entity.Customer
import acal.com.core.domain.entity.Link
import acal.com.core.domain.entity.Place
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("link")
data class LinkModel(

    @Id
    val id: String,
    val number: String,

    val exclusiveMember: Boolean,

    val customerId: String,
    val placeId: String,
    val categoryId: String,

    val active: Boolean,
)

fun LinkModel.toDomain(customer: Customer, place: Place, category: Category): Link = Link(
    id = id,
    number = number,
    customer = customer,
    place = place,
    category = category,
    exclusiveMember = exclusiveMember,
    active = active
)

fun Link.toEntity(): LinkModel = LinkModel(
    id = id,
    number = number,
    customerId = customer.id,
    placeId = place.id,
    categoryId = category.id,
    exclusiveMember = exclusiveMember,
    active = active
)
