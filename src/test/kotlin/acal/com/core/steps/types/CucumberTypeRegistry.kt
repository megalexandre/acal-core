package acal.com.core.steps.types

import acal.com.core.domain.entity.AuditInfo
import acal.com.core.resouces.CustomerModel
import io.cucumber.java.DataTableType
import java.time.LocalDateTime

class CucumberTypeRegistry {

    @DataTableType
    fun customerModelTransformer(entry: Map<String, String>): CustomerModel {
        return CustomerModel(
            id = entry["id"] ?: "",
            auditInfo = AuditInfo(
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now()
            ),
            name = entry["name"] ?: "",
            identityCard = entry["identity_card"] ?: "",
            phoneNumber = entry["phone_number"],
            partnerNumber = entry["partner_number"],
            voter = entry["voter"]?.toBoolean() ?: false
        )
    }
}
