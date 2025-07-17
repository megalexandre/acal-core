package acal.com.core.application.invoice.data.out

import acal.com.core.domain.entity.Category
import acal.com.core.domain.entity.Customer
import acal.com.core.domain.entity.Place
import acal.com.core.domain.entity.Reference
import acal.com.core.domain.entity.WaterMeter
import java.time.LocalDate
import java.time.LocalDateTime

class InvoiceViewResponse (
    val id: String,
    val reference: Reference,
    val number: String,
    val waterMeter: WaterMeter?,

    )
