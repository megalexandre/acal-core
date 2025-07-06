package acal.com.core.application.address

import acal.com.core.application.address.data.`in`.AddressCreateRequest
import acal.com.core.application.address.data.`in`.AddressUpdateRequest
import acal.com.core.application.address.data.`in`.toDomain
import acal.com.core.application.address.data.out.AddressResponse
import acal.com.core.application.address.data.out.addressResponse
import acal.com.core.domain.usecase.address.*
import acal.com.core.infrastructure.exception.DataNotFoundException
import org.slf4j.LoggerFactory
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
    private val delete: AddressDeleteUseCase
) {
    private val logger = LoggerFactory.getLogger(AddressController::class.java)

    @GetMapping
    @ResponseStatus(OK)
    fun getAll(): Collection<AddressResponse> {
        logger.info("Searching for all addresses")
        return findAll.execute().addressResponse().also {
            logger.info("Found {} addresses", it.size)
        }
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    fun getById(@PathVariable id: String): AddressResponse =
        findById.execute(id)
            ?.addressResponse()
            ?.also { logger.info("Address found successfully: {}", it) }
            ?: run {
                logger.error("Address not found with ID: {}", id)
                throw DataNotFoundException("Address not found with ID: $id")
            }

    @ResponseStatus(OK)
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String) {
        logger.info("Deleting address with ID: {}", id)
        delete.execute(id)
        logger.info("Address with ID: {} deleted successfully", id)
    }

    @PostMapping
    @ResponseStatus(CREATED)
    fun create(@RequestBody request: AddressCreateRequest): AddressResponse =
        create.execute(request.toDomain()).addressResponse().also {
            logger.info("Address created successfully: {}", it)
        }

    @PostMapping("all")
    @ResponseStatus(CREATED)
    fun createAll(@RequestBody request: Collection<AddressCreateRequest>): Collection<AddressResponse> {
        logger.info("Creating {} new addresses", request.size)
        val result = saveAll.execute(request.toDomain()).addressResponse()
        logger.info("{} addresses created successfully", result.size)
        return result
    }

    @PutMapping
    @ResponseStatus(OK)
    fun update(@RequestBody request: AddressUpdateRequest): AddressResponse {
        logger.info("Updating address: {}", request)
        val result = create.execute(request.toDomain()).addressResponse()
        logger.info("Address updated successfully: {}", result)
        return result
    }
}
