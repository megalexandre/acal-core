package acal.com.core.application

import CustomerResponse
import acal.com.core.application.data.`in`.CustomerCreateRequest
import acal.com.core.application.data.out.customerCreateResponse
import acal.com.core.domain.datasource.CustomerDataSource
import acal.com.core.domain.usecase.CustomerCreateUseCase
import customerResponse
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/customer")
class CustomerController(
    val customerCreateUseCase: CustomerCreateUseCase,
    val customerDataSource: CustomerDataSource
) {

    @ResponseStatus(CREATED)
    @PostMapping(produces = [APPLICATION_JSON_VALUE])
    fun create(@RequestBody customerCreateRequest: CustomerCreateRequest) =
        customerCreateUseCase.execute(customerCreateRequest.toDomain()).customerCreateResponse()

    @GetMapping(produces = [APPLICATION_JSON_VALUE])
    fun getAll(): List<CustomerResponse> =
        customerDataSource.findAll().customerResponse()

}
