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

    val totalValue: BigDecimal
        get() = values
            .map { it.value }
            .reduce { acc, value -> acc.add(value)  }

    val values: List<InvoiceValue>
        get() = listOf(
            InvoiceValue("Água", category.price.waterValue) ,
            InvoiceValue("Categoria", category.price.partnerValue),
            InvoiceValue("Hidrômetro", waterValue)
        )

    val consumption: Double
        get() = waterMeter?.calculatedConsumption ?: 0.0

    val waterValue: BigDecimal
        get() = waterMeter?.total ?: BigDecimal.ZERO



}

class InvoiceValue(
    val name: String,
    val value: BigDecimal
)