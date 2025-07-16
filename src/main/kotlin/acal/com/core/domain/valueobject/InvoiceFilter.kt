package acal.com.core.domain.valueobject

data class InvoiceFilter(
    val page: Int,
    val size: Int,
    val sortOrders: List<SortOrder>? = null
)