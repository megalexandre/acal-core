package acal.com.core.application.customer

import CustomerResponse
import acal.com.core.application.customer.data.`in`.CustomerCreateRequest
import acal.com.core.application.customer.data.`in`.CustomerUpdateRequest
import acal.com.core.application.customer.data.`in`.toDomain
import acal.com.core.domain.usecase.customer.*
import acal.com.core.domain.valueobject.CustomerFilter
import acal.com.core.infrastructure.exception.DataNotFoundException
import customerResponse
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import org.springframework.web.bind.annotation.*
import response

@RestController
@RequestMapping(
    value = ["/customer"],
)
class CustomerController(
    private val create: CustomerCreateUseCase,
    private val saveAll: CustomerCreateAllUseCase,
    private val findById: CustomerByIdUseCase,
    private val findAll: CustomerFindAllUseCase,
    private val delete: CustomerDeleteUseCase,
    private val paginate: CustomerPaginateUseCase
) {
    private val logger = LoggerFactory.getLogger(CustomerController::class.java)

    @PostMapping("/paginate")
    @ResponseStatus(HttpStatus.OK)
    fun paginate(@RequestBody filter: CustomerFilter): Page<CustomerResponse> =
        paginate.execute(filter).response()

    @PostMapping
    @ResponseStatus(CREATED)
    fun create(@RequestBody request: CustomerCreateRequest): CustomerResponse =
        create.execute(request.toDomain()).customerResponse()

    @PostMapping("all")
    @ResponseStatus(CREATED)
    fun createAll(@RequestBody request: Collection<CustomerCreateRequest>): Collection<CustomerResponse> =
        saveAll.execute(request.toDomain()).customerResponse()

    @DeleteMapping("/{id}")
    @ResponseStatus(OK)
fun delete(@PathVariable id: String) {
        delete.execute(id)
    }

    @PutMapping
    @ResponseStatus(OK)
    fun update(@RequestBody request: CustomerUpdateRequest): CustomerResponse =
        create.execute(request.toDomain()).customerResponse()

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    fun getById(@PathVariable id: String): CustomerResponse? =
        findById.execute(id)?.customerResponse()
            ?: throw DataNotFoundException("Cliente não encontrado com o ID: $id")

    @GetMapping
    @ResponseStatus(OK)
    fun get(): Collection<CustomerResponse> =
        findAll.execute().customerResponse()
}
