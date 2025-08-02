package acal.com.core.domain.valueobject

import acal.com.core.comons.normalize
import java.math.BigDecimal

data class SortOrder(
    val property: String,
    val direction: String
)

data class LinkFilter(
    val page: Int,
    val size: Int,
    val name: String? = null,
    val categoryId: String? = null,
    val addressId: String? = null,
    val groupName: String? = null,

    val letter: String? = null,
    val number: String? = null,

    val total: BigDecimal? = null,
    val sortOrders: List<SortOrder>? = null
){
    val normalized = name.normalize()
}
