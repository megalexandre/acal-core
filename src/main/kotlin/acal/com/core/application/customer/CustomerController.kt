package acal.com.core.application.customer

import CustomerResponse
import acal.com.core.application.customer.data.`in`.CustomerCreateRequest
import acal.com.core.application.customer.data.`in`.CustomerUpdateRequest
import acal.com.core.application.customer.data.`in`.toDomain
import acal.com.core.domain.usecase.customer.CustomerByIdUseCase
import acal.com.core.domain.usecase.customer.CustomerCreateAllUseCase
import acal.com.core.domain.usecase.customer.CustomerCreateUseCase
import acal.com.core.infrastructure.exception.DataNotFoundException
import customerResponse
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(
    value = ["/customer"],
)
class CustomerController(
    val create: CustomerCreateUseCase,
    val saveAll: CustomerCreateAllUseCase,
    val findById: CustomerByIdUseCase
) {

    @PostMapping
    @ResponseStatus(CREATED)
    fun create(@RequestBody request: CustomerCreateRequest): CustomerResponse =
        create.execute(request.toDomain()).customerResponse()

    @PutMapping
    @ResponseStatus(OK)
    fun update(@RequestBody request: CustomerUpdateRequest): CustomerResponse =
        create.execute(request.toDomain()).customerResponse()

    @PostMapping("all")
    @ResponseStatus(CREATED)
    fun createAll(@RequestBody request: Collection<CustomerCreateRequest>): Collection<CustomerResponse> =
        saveAll.execute(request.toDomain()).customerResponse()

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    fun getById(@PathVariable id: String): CustomerResponse? =
        findById.execute(id)?.customerResponse()
            ?: throw DataNotFoundException("Cliente não encontrado com o ID: $id")
}
