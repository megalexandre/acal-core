package acal.com.core.application.category.data.out

import acal.com.core.domain.entity.Category
import acal.com.core.domain.enums.Group
import java.math.BigDecimal
import java.time.LocalDateTime

data class CategoryResponse(
    val id: String,
    val name: String,
    val waterValue: BigDecimal,
    val partnerValue: BigDecimal,
    val total: BigDecimal,
    val group: Group,
    val isHydrometer: Boolean
)

fun Category.categoryResponse() = CategoryResponse(
    id = id,
    name = name,
    waterValue = price.waterValue,
    partnerValue = price.partnerValue,
    total = total,
    group = group,
    isHydrometer = isHydrometer
)

fun List<Category>.categoryResponse() = this.map { it.categoryResponse() }

fun Collection<Category>.categoryResponse() = this.map { it.categoryResponse() }
