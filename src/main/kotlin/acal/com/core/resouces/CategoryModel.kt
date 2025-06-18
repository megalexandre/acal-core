package acal.com.core.resouces

import acal.com.core.domain.entity.Category
import acal.com.core.domain.entity.CategoryPrice
import acal.com.core.domain.enums.Group
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal

@Document(collection = "category")
data class CategoryModel(
    @Id
    val id: String,
    val name: String,
    val waterValue: BigDecimal,
    val partnerValue: BigDecimal,
    val group: Group,
    val isHydrometer: Boolean
)

fun CategoryModel.toDomain() = Category(
    id = id,
    name = name,
    price = CategoryPrice(
        waterValue = waterValue,
        partnerValue = partnerValue
    ),
    group = group,
    isHydrometer = isHydrometer
)

fun Category.toEntity() = CategoryModel(
    id = id,
    name = name,
    waterValue = price.waterValue,
    partnerValue = price.partnerValue,
    group = group,
    isHydrometer = isHydrometer
)
