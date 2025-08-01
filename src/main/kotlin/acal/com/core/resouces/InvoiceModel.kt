package acal.com.core.resouces

import acal.com.core.domain.entity.Invoice
import acal.com.core.domain.entity.Reference
import acal.com.core.domain.entity.WaterMeter
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

@Document("invoice")
data class InvoiceModel (

    @Id
    val id: String,
    val reference: String,
    val number: String,

    val waterMeter: WaterMeterModel?,

    val customer: CustomerModel,
    val place: PlaceModel,
    val category: CategoryModel,

    val dueDate: LocalDate,
    val paidAt: LocalDateTime?,
)

@Document("water_meter")
data class WaterMeterModel(
    val start: Double,
    val end: Double,
    val value: BigDecimal
)

fun WaterMeterModel.toDomain(): WaterMeter = WaterMeter(
    start = start,
    end = end,
    value = value
)

fun WaterMeter.toEntity(): WaterMeterModel = WaterMeterModel(
    start = start,
    end = end,
    value = value
)

fun InvoiceModel.toDomain(): Invoice = Invoice(
    id = id,
    reference = Reference.of(reference),
    number = number,
    waterMeter = waterMeter?.toDomain(),
    customer = customer.toDomain(),
    place = place.toDomain(),
    category = category.toDomain(),
    dueDate = dueDate,
    paidAt = paidAt,
    waterQuality = null,
)

fun Invoice.toEntity(): InvoiceModel = InvoiceModel(
    id = id,
    number = number,
    reference = reference.toString(),
    waterMeter = waterMeter?.toEntity(),
    customer = customer.toEntity(),
    place = place.toEntity(),
    category = category.toEntity(),
    dueDate = dueDate,
    paidAt = paidAt
)