package acal.com.core.application.invoice.`in`

import acal.com.core.application.category.data.`in`.CategoryRequest
import acal.com.core.application.customer.data.`in`.CustomerRequest
import acal.com.core.application.place.data.`in`.PlaceRequest
import acal.com.core.comons.Id
import acal.com.core.domain.entity.Invoice
import acal.com.core.domain.entity.Reference
import acal.com.core.domain.entity.WaterMeter
import jakarta.validation.constraints.NotBlank
import java.time.LocalDate

data class InvoiceCreateRequest (

    val id: String?,

    @field:NotBlank(message = "Reference is required.")
    val reference: Reference,

    @field:NotBlank(message = " Number is required.")
    val number: String,

    val waterMeter: WaterMeter,

    @field:NotBlank
    val dueDate: LocalDate,

    @field:NotBlank
    val category: CategoryRequest,

    @field:NotBlank
    val customer: CustomerRequest,

    @field:NotBlank
    val place: PlaceRequest,

    @field:NotBlank
    val linkId: String,
){
    fun toDomain() = Invoice(
        id = id ?: Id.random(),
        reference = reference,
        number = number,
        waterMeter = waterMeter,
        dueDate = dueDate,
        category = category.toDomain(),
        customer = customer.toDomain(),
        place = place.toDomain(),
        paidAt = null,
        waterQuality =  null,
        linkId = linkId,
    )

}

fun Collection<InvoiceCreateRequest>.toDomain(): List<Invoice> = this.map { it.toDomain() }

