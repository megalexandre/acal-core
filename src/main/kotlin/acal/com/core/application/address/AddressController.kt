package acal.com.core.application.address

import acal.com.core.application.address.data.`in`.AddressCreateRequest
import acal.com.core.application.address.data.`in`.AddressUpdateRequest
import acal.com.core.application.address.data.`in`.toDomain
import acal.com.core.application.address.data.out.AddressResponse
import acal.com.core.application.address.data.out.addressResponse
import acal.com.core.domain.usecase.address.AddressByIdUseCase
import acal.com.core.domain.usecase.address.AddressCreateAllUseCase
import acal.com.core.domain.usecase.address.AddressCreateUseCase
import acal.com.core.infrastructure.exception.DataNotFoundException
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(
    value = ["/address"],
)
class AddressController(
    val create: AddressCreateUseCase,
    val saveAll: AddressCreateAllUseCase,
    val findById: AddressByIdUseCase
) {

    @PostMapping
    @ResponseStatus(CREATED)
    fun create(@RequestBody request: AddressCreateRequest): AddressResponse =
        create.execute(request.toDomain()).addressResponse()

    @PutMapping
    @ResponseStatus(OK)
    fun update(@RequestBody request: AddressUpdateRequest): AddressResponse =
        create.execute(request.toDomain()).addressResponse()

    @PostMapping("all")
    @ResponseStatus(CREATED)
    fun createAll(@RequestBody request: Collection<AddressCreateRequest>): Collection<AddressResponse> =
        saveAll.execute(request.toDomain()).addressResponse()

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    fun getById(@PathVariable id: String): AddressResponse? =
        findById.execute(id)?.addressResponse()
            ?: throw DataNotFoundException("Endereço não encontrado com o ID: $id")
}
