package acal.com.core.domain.entity

import java.math.BigDecimal
import java.time.LocalDateTime

data class Link (

     val id: String,
     val number: String,
     val customer: Customer,
     val place: Place,
     val category: Category,
     val exclusiveMember: Boolean,
     val references: Collection<Reference>?,
     val active: Boolean,
     val deletedAt: LocalDateTime?
){
     val total: BigDecimal
          get() = category.total

}
