package acal.com.core.domain.valueobject

import java.time.LocalDate

data class FinancialRecordFilter(
    val dateStart: LocalDate? = null,
    val dateEnd: LocalDate? = null,
    val page: Int = 0,
    val size: Int = 20,
    val sortOrders: List<SortOrder>? = null,
)