package acal.com.core.domain.entity

import java.math.BigDecimal

data class Link (

     val id: String,
     val number: String,
     val customer: Customer,
     val place: Place,
     val category: Category,
     val exclusiveMember: Boolean,
     val active: Boolean,
){
     val total: BigDecimal
          get() = category.total

}
