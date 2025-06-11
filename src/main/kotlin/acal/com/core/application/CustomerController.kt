package acal.com.core.application

import acal.com.core.application.data.`in`.CustomerCreateRequest
import acal.com.core.application.data.out.CustomerCreateResponse
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/customer")
class CustomerController {

    @ResponseStatus(CREATED)
    @GetMapping(produces = [APPLICATION_JSON_VALUE])
    fun create(customerCreateRequest: CustomerCreateRequest) = CustomerCreateResponse()

}

