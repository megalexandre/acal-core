package acal.com.core.domain.valueobject

data class FinancialRecordFilter(
    val page: Int = 0,
    val size: Int = 20,
    val sortOrders: List<SortOrder>? = null,
)