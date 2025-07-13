package acal.com.core.resouces

import acal.com.core.domain.entity.Address
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("address")
data class AddressModel(

    @Id
    val id: String,
    val name: String,
)

fun AddressModel.toDomain(): Address = Address(
    id = id,
    name = name,
)

fun Address.toEntity(): AddressModel = AddressModel(
    id = id,
    name = name,
)

