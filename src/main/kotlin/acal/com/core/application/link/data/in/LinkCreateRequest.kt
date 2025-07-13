package acal.com.core.application.link.data.`in`

import acal.com.core.comons.Id
import acal.com.core.domain.valueobject.LinkCreate
import jakarta.validation.constraints.NotBlank

data class LinkCreateRequest (

    val id: String?,

    @field:NotBlank(message = "Number is required.")
    val number: String,

    @field:NotBlank(message = "Customer is required.")
    val customerId: String,

    @field:NotBlank(message = "Address is required.")
    val placeId: String,

    @field:NotBlank(message = "Category is required.")
    val categoryId: String,

    val exclusiveMember: Boolean = true,

) {
    fun toDomain() = LinkCreate(
        id = id ?: Id.random(),
        number = number,
        customerId = customerId,
        placeId = placeId,
        categoryId = categoryId,
        exclusiveMember = exclusiveMember ,
        active = true,
    )
}


