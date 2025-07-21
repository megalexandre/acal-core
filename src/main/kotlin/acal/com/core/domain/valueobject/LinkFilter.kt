package acal.com.core.domain.valueobject

import java.math.BigDecimal

data class SortOrder(
    val property: String,
    val direction: String
)

data class LinkFilter(
    val page: Int,
    val size: Int,
    val name: String? = null,
    val category: String? = null,
    val address: String? = null,

    val letter: String? = null,
    val number: String? = null,

    val total: BigDecimal? = null,
    val sortOrders: List<SortOrder>? = null
)