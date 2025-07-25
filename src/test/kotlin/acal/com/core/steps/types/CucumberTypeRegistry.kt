package acal.com.core.steps.types

import acal.com.core.comons.normalize
import acal.com.core.domain.enums.Group
import acal.com.core.resouces.AddressModel
import acal.com.core.resouces.CategoryModel
import acal.com.core.resouces.CustomerModel
import io.cucumber.java.DataTableType
import java.math.BigDecimal
import java.time.LocalDateTime

class CucumberTypeRegistry {

    @DataTableType
    fun customerModelTransformer(entry: Map<String, String>): CustomerModel =
        CustomerModel(
            id = entry["id"] ?: "",
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now(),
            name = entry["name"] ?: "",
            identityCard = entry["identity_card"] ?: "",
            phoneNumber = entry["phone_number"],
            partnerNumber = entry["partner_number"],
            voter = entry["voter"]?.toBoolean() ?: false,
            normalizedName = (entry["name"] ?: "").normalize()
        )

    @DataTableType
    fun addressModelTransformer(entry: Map<String, String>): AddressModel =
        AddressModel(
            id = entry["id"] ?: "",
            name = entry["name"] ?: "",
        )

    @DataTableType
    fun categoryModelTransformer(entry: Map<String, String>): CategoryModel =
        CategoryModel(
            id = entry["id"] ?: acal.com.core.comons.Id.random(),
            name = entry["name"] ?: "",
            waterValue = entry["water_value"]?.toBigDecimal() ?: BigDecimal.ZERO,
            partnerValue = entry["partner_value"]?.toBigDecimal() ?: BigDecimal.ZERO,
            group = Group.valueOf(entry["group"]!!),
            isHydrometer = entry["is_hydrometer"]?.toBoolean() ?: false
        )

}
