package acal.com.core.domain.valueobject

data class LinkCreate (
     val id: String,
     val number: String,
     val customerId: String,
     val placeId: String,
     val categoryId: String,
     val exclusiveMember: Boolean,
     val active: Boolean,
)
