package acal.com.core.application.category.data.`in`

import acal.com.core.domain.entity.Category
import acal.com.core.domain.entity.CategoryPrice
import acal.com.core.domain.enums.Group
import jakarta.validation.constraints.NotBlank
import java.math.BigDecimal

data class CategoryUpdateRequest(
    @field:NotBlank(message = "Id is required.")
    val id: String,

    @field:NotBlank(message = "Name is required.")
    val name: String,

    @field:NotBlank(message = "Water Value is required.")
    val waterValue: BigDecimal,

    @field:NotBlank(message = "Parter value is required.")
    val partnerValue: BigDecimal,

    @field:NotBlank(message = "Group is required.")
    val group: Group,

    @field:NotBlank(message = "is Hydrometer is required.")
    val isHydrometer: Boolean
) {
    fun toDomain() = Category(
        id = id,
        name = name,
        price = CategoryPrice(
            waterValue = waterValue,
            partnerValue = partnerValue
        ),
        group = group,
        isHydrometer = isHydrometer
    )
}
