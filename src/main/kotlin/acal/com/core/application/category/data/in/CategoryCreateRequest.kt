package acal.com.core.application.category.data.`in`

import acal.com.core.comons.Id
import acal.com.core.domain.entity.Category
import acal.com.core.domain.entity.CategoryPrice
import acal.com.core.domain.enums.Group
import jakarta.validation.constraints.NotBlank
import java.math.BigDecimal

data class CategoryCreateRequest(
    val id: String?,

    @field:NotBlank(message = "O nome da categoria não pode ser vazio")
    val name: String,

    @field:NotBlank(message = "O nome da valor da água não pode ser vazio")
    val waterValue: BigDecimal,

    @field:NotBlank(message = "O nome da valor do socio não pode ser vazio")
    val partnerValue: BigDecimal,

    @field:NotBlank(message = "O nome da categoria não pode ser vazio")
    val group: Group,

    val isHydrometer: Boolean = false
) {
    fun toDomain() = Category(
        id = id ?: Id.random(),
        name = name.trimIndent(),
        price = CategoryPrice(
            waterValue = waterValue,
            partnerValue = partnerValue
        ),
        group = group,
        isHydrometer = isHydrometer
    )
}

fun Collection<CategoryCreateRequest>.toDomain() = this.map { it.toDomain() }
