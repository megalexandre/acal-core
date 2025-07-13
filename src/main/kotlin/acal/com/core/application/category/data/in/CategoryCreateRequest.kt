package acal.com.core.application.category.data.`in`

import acal.com.core.comons.Id
import acal.com.core.domain.entity.Category
import acal.com.core.domain.entity.CategoryPrice
import acal.com.core.domain.enums.Group
import java.math.BigDecimal

data class CategoryCreateRequest(
    val id: String,
    val name: String,
    val waterValue: BigDecimal,
    val partnerValue: BigDecimal,
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
