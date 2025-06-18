package acal.com.core.domain.entity

import acal.com.core.domain.enums.Group
import java.math.BigDecimal

class Category(
    val id: String,
    val name: String,
    val price: CategoryPrice,
    val group: Group,
    val isHydrometer: Boolean
) {

    val total: BigDecimal
        get() = price.total

}

class CategoryPrice(
    val waterValue: BigDecimal,
    val partnerValue: BigDecimal
) {

    val total: BigDecimal
        get() = waterValue + partnerValue
}
