package acal.com.core.application.waterQuality

import acal.com.core.application.waterQuality.data.`in`.WaterQualityRequest
import acal.com.core.application.waterQuality.data.out.WaterQualityResponse
import acal.com.core.application.waterQuality.data.out.response
import acal.com.core.domain.usecase.waterquality.WaterQualityCreateUseCase
import acal.com.core.domain.usecase.waterquality.WaterQualityFindAllUseCase
import acal.com.core.domain.usecase.waterquality.WaterQualityDeleteUseCase
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(
    value = ["/water-quality"],
)
class WaterQualityController(
    private val create: WaterQualityCreateUseCase,
    private val findAll: WaterQualityFindAllUseCase,
    private val delete: WaterQualityDeleteUseCase,
) {

    @PostMapping
    @ResponseStatus(CREATED)
    fun create(@RequestBody request: WaterQualityRequest): WaterQualityResponse =
        create.execute(request.toDomain()).response()

    @PostMapping("/all")
    @ResponseStatus(CREATED)
    fun create(@RequestBody request: Collection<WaterQualityRequest>) =
        request.forEach { create.execute(it.toDomain()).response() }

    @GetMapping
    @ResponseStatus(OK)
    fun getAll(): Collection<WaterQualityResponse> =
        findAll.execute().map { it.response() }

    @ResponseStatus(OK)
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String) {
        delete.execute(id)
    }
}
