package acal.com.core.application

import CustomerResponse
import acal.com.core.application.data.`in`.CustomerCreateRequest
import acal.com.core.application.data.`in`.CustomerUpdateRequest
import acal.com.core.application.data.out.customerCreateResponse
import acal.com.core.domain.datasource.CustomerDataSource
import acal.com.core.domain.usecase.customer.CustomerCreateUseCase
import customerResponse
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(
    value = ["/customer"],
    produces = [APPLICATION_JSON_VALUE],
    consumes = [APPLICATION_JSON_VALUE]
)
class CustomerController(
    val customerCreateUseCase: CustomerCreateUseCase,
    val customerDataSource: CustomerDataSource
) {

    @PostMapping
    @ResponseStatus(CREATED)
    fun create(@RequestBody request: CustomerCreateRequest) =
        customerCreateUseCase.execute(request.toDomain()).customerCreateResponse()

    @PutMapping
    @ResponseStatus(OK)
    fun update(@RequestBody request: CustomerUpdateRequest) =
        customerCreateUseCase.execute(request.toDomain()).customerCreateResponse()


    @GetMapping
    @ResponseStatus(OK)
    fun get(): List<CustomerResponse> =
        customerDataSource.findAll().customerResponse()

}
