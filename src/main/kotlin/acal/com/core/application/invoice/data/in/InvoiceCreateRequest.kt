package acal.com.core.application.invoice.data.`in`

import acal.com.core.comons.Id
import acal.com.core.domain.entity.*
import jakarta.validation.constraints.NotBlank
import java.time.LocalDate

data class InvoiceCreateRequest (

    val id: String?,

    @field:NotBlank(message = "Reference is required.")
    val reference: Reference,

    @field:NotBlank(message = " Number is required.")
    val number: String,

    val waterMeter: WaterMeter?,

    @field:NotBlank
    val customer: Customer,

    @field:NotBlank
    val place: Place,

    @field:NotBlank
    val category: Category,

    @field:NotBlank
    val dueDate: LocalDate,

) {
    fun toDomain() = Invoice(
        id = id ?: Id.random(),
        number = number,
        reference = reference,
        waterMeter = waterMeter,
        customer = customer,
        place = place,
        category = category,
        dueDate = dueDate,
        paidAt = null,
    )
}


