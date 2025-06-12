package acal.com.core.application.data.out

import acal.com.core.domain.entity.Customer

class CustomerCreateResponse(
    val id: String,
)

fun Customer.customerCreateResponse() = CustomerCreateResponse(
    id = id,
)