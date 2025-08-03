package acal.com.core.application.invoice.data.out

import CustomerResponse
import acal.com.core.application.category.data.out.CategoryResponse
import acal.com.core.application.category.data.out.categoryResponse
import acal.com.core.application.place.data.out.PlaceResponse
import acal.com.core.application.place.data.out.placeResponse
import acal.com.core.domain.entity.*
import acal.com.core.domain.enums.InvoiceStatus
import customerResponse
import org.springframework.data.domain.Page
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

class InvoiceViewResponse (
    val id: String,
    val reference: Reference,
    val number: String,
    val waterMeter: WaterMeter?,
    val waterQuality: WaterQuality?,
    val customer: CustomerResponse,
    val category: CategoryResponse,
    val place: PlaceResponse,
    val dueDate: LocalDate,
    val paidAt: LocalDateTime?,

    val total: BigDecimal,
    val values: List<InvoiceValue>,
    val consumption: Double,
    val waterValue: BigDecimal,
    val status: InvoiceStatus
)

fun Invoice.toView(): InvoiceViewResponse = InvoiceViewResponse(
    id = id,
    reference = reference,
    number = number,
    waterMeter = waterMeter,
    customer = customer.customerResponse(),
    category = category.categoryResponse(),
    place = place.placeResponse(),
    dueDate = dueDate,
    paidAt = paidAt,
    total = totalValue,
    values = values,
    consumption = consumption,
    waterValue  = waterValue,
    status = status,
    waterQuality = waterQuality,
)

fun Collection<Invoice>.toView(): List<InvoiceViewResponse> = this.map { it.toView() }

fun Page<Invoice>.response(): Page<InvoiceViewResponse> = this.map { it.toView() }