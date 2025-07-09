package acal.com.core.infrastructure.event

import acal.com.core.domain.entity.Category

data class CategoryEvent(
    val eventType: EventType,
    val category: Category
)