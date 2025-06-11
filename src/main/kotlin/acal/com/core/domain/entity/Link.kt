package acal.com.core.domain.entity

data class Link (

     val id: String,
     val auditInfo: AuditInfo,
     val number: String,

     val customer: Customer,
     val address: Address,
     val category: Category,

     val exclusiveMember: Boolean,
     val active: Boolean,

     )
