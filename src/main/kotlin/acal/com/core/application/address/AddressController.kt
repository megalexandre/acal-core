package acal.com.core.application.address

import acal.com.core.application.address.data.`in`.AddressCreateRequest
import acal.com.core.application.address.data.`in`.AddressUpdateRequest
import acal.com.core.application.address.data.`in`.toDomain
import acal.com.core.application.address.data.out.AddressResponse
import acal.com.core.application.address.data.out.addressResponse
import acal.com.core.domain.usecase.address.*
import acal.com.core.infrastructure.exception.DataNotFoundException
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(
    value = ["/address"],
)
class AddressController(
    private val create: AddressCreateUseCase,
    private val saveAll: AddressCreateAllUseCase,
    private val findById: AddressByIdUseCase,
    private val findAll: AddressFindAllUseCase,
    private val deleteUseCase: AddressDeleteUseCase
) {

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    fun getById(@PathVariable id: String): AddressResponse? =
        findById.execute(id)?.addressResponse()
            ?: throw DataNotFoundException("Endereço não encontrado com o ID: $id")

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String) {
        deleteUseCase.execute(id)
    }

    @GetMapping
    @ResponseStatus(OK)
    fun getAll(): Collection<AddressResponse> =
        findAll.execute().addressResponse()

    @PostMapping
    @ResponseStatus(CREATED)
    fun create(@RequestBody request: AddressCreateRequest): AddressResponse =
        create.execute(request.toDomain()).addressResponse()

    @PostMapping("all")
    @ResponseStatus(CREATED)
    fun createAll(@RequestBody request: Collection<AddressCreateRequest>): Collection<AddressResponse> =
        saveAll.execute(request.toDomain()).addressResponse()

    @PutMapping
    @ResponseStatus(OK)
    fun update(@RequestBody request: AddressUpdateRequest): AddressResponse =
        create.execute(request.toDomain()).addressResponse()

}
