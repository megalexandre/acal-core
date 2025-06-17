package acal.com.core.application

import CustomerResponse
import acal.com.core.application.data.`in`.CustomerCreateRequest
import acal.com.core.application.data.`in`.CustomerUpdateRequest
import acal.com.core.application.data.`in`.toDomain
import acal.com.core.domain.datasource.CustomerDataSource
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
    val customerCreateUseCase: CustomerCreateUseCase,
    val customerCreateAllUseCase: CustomerCreateAllUseCase,
    val customerDataSource: CustomerDataSource
) {

    @PostMapping
    @ResponseStatus(CREATED)
    fun create(@RequestBody request: CustomerCreateRequest): CustomerResponse =
        customerCreateUseCase.execute(request.toDomain()).customerResponse()

    @PostMapping("all")
    @ResponseStatus(CREATED)
    fun createAll(@RequestBody request: Collection<CustomerCreateRequest>): Collection<CustomerResponse> =
        customerCreateAllUseCase.execute(request.toDomain()).customerResponse()

    @PutMapping
    @ResponseStatus(OK)
    fun update(@RequestBody request: CustomerUpdateRequest): CustomerResponse =
        customerCreateUseCase.execute(request.toDomain()).customerResponse()

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    fun getById(@PathVariable id: String): CustomerResponse? =
        customerDataSource.findById(id)?.customerResponse()
            ?: throw DataNotFoundException("Cliente não encontrado com o ID: $id")
}
