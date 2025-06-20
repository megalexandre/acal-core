import acal.com.core.application.category.data.out.CategoryResponse
import acal.com.core.application.category.data.out.categoryResponse
import acal.com.core.application.place.data.out.PlaceResponse
import acal.com.core.application.place.data.out.placeResponse
import acal.com.core.domain.entity.Link
import java.math.BigDecimal

data class LinkResponse(
    val id: String,
    val number: String,
    val customer: CustomerResponse,
    val category: CategoryResponse,
    val place: PlaceResponse,
    val total: BigDecimal,
    val exclusiveMember: Boolean,
    val active: Boolean,
)

fun Link.linkResponse() = LinkResponse(
    id = id,
    number = number,
    customer = customer.customerResponse(),
    category = category.categoryResponse(),
    place = place.placeResponse(),
    total = total,
    exclusiveMember = exclusiveMember,
    active = active,
)

fun List<Link>.linkResponse() = this.map { it.linkResponse() }
fun Collection<Link>.linkResponse() =  this.map { it.linkResponse() }
