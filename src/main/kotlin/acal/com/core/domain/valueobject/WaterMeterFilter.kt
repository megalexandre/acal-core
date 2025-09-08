package acal.com.core.domain.valueobject

import acal.com.core.domain.entity.Reference

data class WaterMeterFilter(
    val page: Int = 0 ,
    val size: Int = 20,
    val sortOrders: List<SortOrder>? = null,
    val reference: Reference?,
)