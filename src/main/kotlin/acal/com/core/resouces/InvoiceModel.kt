package acal.com.core.resouces

import acal.com.core.domain.entity.Invoice
import acal.com.core.domain.entity.Reference
import acal.com.core.domain.entity.WaterMeter
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Month
import java.time.Year

@Document("invoice")
data class InvoiceModel (

    @Id
    val id: String,
    val reference: ReferenceModel,
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

@Document("reference")
data class ReferenceModel(
    val value: String
)

fun ReferenceModel.toDomain(): Reference {
    val parts = value.split("-")
    val month = Month.valueOf(parts[0])
    val year = Year.of(parts[1].toInt())
    return Reference(year, month)
}

fun Reference.toEntity(): ReferenceModel {
    val referenceValue = "${month}-${year.value}"
    return ReferenceModel(value = referenceValue)
}

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
    reference = reference.toDomain(),
    number = number,
    waterMeter = waterMeter?.toDomain(),
    customer = customer.toDomain(),
    place = place.toDomain(),
    category = category.toDomain(),
    dueDate = dueDate,
    paidAt = paidAt
)

fun Invoice.toEntity(): InvoiceModel = InvoiceModel(
    id = id,
    number = number,
    reference = reference.toEntity(),
    waterMeter = waterMeter?.toEntity(),
    customer = customer.toEntity(),
    place = place.toEntity(),
    category = category.toEntity(),
    dueDate = dueDate,
    paidAt = paidAt
)