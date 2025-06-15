package acal.com.core.configuration

import acal.com.core.domain.entity.AuditInfo
import acal.com.core.resouces.CustomerModel
import io.cucumber.java.DataTableType
import java.time.LocalDateTime.now

class MapConfig {

    class DataTableConfig {

        @DataTableType
        fun clienteEntry(entry: Map<String?, String>): CustomerModel =
            CustomerModel(
                id = entry["id"]!!,
                auditInfo = AuditInfo(now(), now()),
                identityCard = entry["identityCard"]!!,
                name = entry["name"]!!,
                phoneNumber = entry["phoneNumber"],
                partnerNumber = entry["partnerNumber"],
                voter = entry["voter"].toBoolean()
            )
        }

}