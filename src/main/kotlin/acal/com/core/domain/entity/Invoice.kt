package acal.com.core.domain.entity

import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

class Invoice (
    val id: String,
    val reference: Reference,
    val number: String,
    val waterMeter: WaterMeter?,

    val customer: Customer,
    val place: Place,
    val category: Category,

    val dueDate: LocalDate,
    val paidAt: LocalDateTime?,

){

    val value: BigDecimal
        get() = category.total.plus(waterValue)

    val consumption: Double
        get() = waterMeter?.consumption ?: 0.0

    val waterValue: BigDecimal
        get() = waterMeter?.value ?: BigDecimal.ZERO

    val values: List<invoiceValue>
        get() = listOf(
            invoiceValue("Água", category.price.waterValue) ,
            invoiceValue("Categoria", category.price.partnerValue),
            invoiceValue("Hidrômetro",waterValue)
        )
}

class invoiceValue(
    val name: String,
    val value: BigDecimal
)