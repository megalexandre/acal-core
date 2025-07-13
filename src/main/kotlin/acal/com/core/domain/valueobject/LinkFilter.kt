package acal.com.core.domain.valueobject

data class SortOrder(
    val property: String,
    val direction: String
)

data class LinkFilter(
    val page: Int,
    val size: Int,
    val name: String? = null,
    val sortOrders: List<SortOrder>? = null
)