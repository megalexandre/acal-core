package acal.com.core.application.category.data.`in`

import acal.com.core.domain.entity.Category
import acal.com.core.domain.entity.CategoryPrice
import acal.com.core.domain.enums.Group
import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal

data class CategoryUpdateRequest(
    @field:NotBlank(message = "Id is required.")
    val id: String,

    @field:NotBlank(message = "Name is required.")
    val name: String,

    @field:NotNull(message = "Water Value is required.")
    @field:DecimalMin(value = "0.00", inclusive = false, message = "Water values must be greater than zero")
    val waterValue: BigDecimal,

    @field:NotNull(message = "Parter Value is required.")
    @field:DecimalMin(value = "0.00", inclusive = false, message = "Partner values must be greater than zero")
    val partnerValue: BigDecimal,

    @field:NotNull(message = "Group is required.")
    val group: Group,

    @field:NotNull(message = "is Hydrometer is required.")
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
