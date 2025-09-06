package acal.com.core.domain.valueobject

import acal.com.core.domain.entity.Address
import acal.com.core.domain.entity.Reference

data class InvoiceFilter(
    val page: Int = 0 ,
    val size: Int = 20,
    val sortOrders: List<SortOrder>? = null,
    val address: Address? = null,
    val reference: Reference?,
    val number: String?,
    val status: String? = null,
){
    val paid: Boolean
        get() = status == "paid"

    val notPaid: Boolean
        get() = status == "not_paid"
}