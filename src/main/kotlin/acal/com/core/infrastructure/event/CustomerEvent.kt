package acal.com.core.infrastructure.event

import acal.com.core.domain.entity.Customer

data class CustomerEvent(
    val eventType: EventType,
    val customer: Customer
)