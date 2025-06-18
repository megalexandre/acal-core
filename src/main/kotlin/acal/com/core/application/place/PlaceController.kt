package acal.com.core.application.place

import acal.com.core.application.place.data.`in`.PlaceCreateRequest
import acal.com.core.application.place.data.`in`.PlaceUpdateRequest
import acal.com.core.application.place.data.`in`.toDomain
import acal.com.core.application.place.data.out.PlaceResponse
import acal.com.core.application.place.data.out.placeResponse
import acal.com.core.domain.usecase.place.PlaceByIdUseCase
import acal.com.core.domain.usecase.place.PlaceCreateAllUseCase
import acal.com.core.domain.usecase.place.PlaceCreateUseCase
import acal.com.core.infrastructure.exception.DataNotFoundException
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(
    value = ["/place"],
)
class PlaceController(
    val create: PlaceCreateUseCase,
    val saveAll: PlaceCreateAllUseCase,
    val findById: PlaceByIdUseCase
) {

    @PostMapping
    @ResponseStatus(CREATED)
    fun create(@RequestBody request: PlaceCreateRequest): PlaceResponse =
        create.execute(request.toDomain()).placeResponse()

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
}
