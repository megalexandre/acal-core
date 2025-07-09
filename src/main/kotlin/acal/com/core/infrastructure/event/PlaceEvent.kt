package acal.com.core.infrastructure.event

import acal.com.core.domain.entity.Place

data class PlaceEvent(
    val eventType: EventType,
    val place: Place
)