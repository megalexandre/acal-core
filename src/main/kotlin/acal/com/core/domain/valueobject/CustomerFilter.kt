package acal.com.core.domain.valueobject

data class CustomerFilter(
    val page: Int,
    val size: Int,
    val name: String? = null,
    val identityCard: String? = null,
    val sortOrders: List<SortOrder>? = null
)