package acal.com.core.domain.valueobject

import acal.com.core.domain.entity.Category
import acal.com.core.domain.entity.Place
import acal.com.core.domain.entity.Reference
import acal.com.core.domain.entity.WaterMeter
import java.time.LocalDate

class InvoiceCreate(
    val id: String?,
    val reference: Reference,
    val number: String,
    val waterMeter: WaterMeter?,
    val customerId: String,
    val place: Place,
    val category: Category,
    val dueDate: LocalDate,
)