package acal.com.core.application.place

import acal.com.core.application.place.data.`in`.PlaceCreateRequest
import acal.com.core.application.place.data.`in`.PlaceUpdateRequest
import acal.com.core.application.place.data.`in`.toDomain
import acal.com.core.application.place.data.out.PlaceResponse
import acal.com.core.application.place.data.out.placeResponse
import acal.com.core.application.place.data.out.response
import acal.com.core.domain.usecase.place.*
import acal.com.core.domain.valueobject.PlaceFilter
import acal.com.core.infrastructure.exception.DataNotFoundException
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(
    value = ["/place"],
)
class PlaceController(
    private val create: PlaceCreateUseCase,
    private val saveAll: PlaceCreateAllUseCase,
    private val findById: PlaceByIdUseCase,
    private val findAll: PlaceFindAllUseCase,
    private val delete: PlaceDeleteUseCase,
    private val paginate: PlacePaginateUseCase,
) {

    @PostMapping
    @ResponseStatus(CREATED)
    fun create(@RequestBody request: PlaceCreateRequest): PlaceResponse =
        create.execute(request.toDomain()).placeResponse()

    @ResponseStatus(OK)
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String) {
        delete.execute(id)
    }

    @PutMapping
    @ResponseStatus(OK)
    fun update(@RequestBody request: PlaceUpdateRequest): PlaceResponse =
        create.execute(request.toDomain()).placeResponse()

    @PostMapping("all")
    @ResponseStatus(CREATED)
    fun createAll(@RequestBody request: Collection<PlaceCreateRequest>): Collection<PlaceResponse> =
        saveAll.execute(request.toDomain()).placeResponse()

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    fun getById(@PathVariable id: String): PlaceResponse? =
        findById.execute(id)?.placeResponse()
            ?: throw DataNotFoundException("Local não encontrado com o ID: $id")

    @GetMapping
    @ResponseStatus(OK)
    fun getAll(): Collection<PlaceResponse> =
        findAll.execute().map { it.placeResponse() }

    @PostMapping("/paginate")
    @ResponseStatus(HttpStatus.OK)
    fun paginate(@RequestBody filter: PlaceFilter): Page<PlaceResponse> =
        paginate.execute(filter).response()

}
