package acal.com.core.application.waterQuality

import acal.com.core.application.waterQuality.data.`in`.WaterQualityRequest
import acal.com.core.application.waterQuality.data.out.WaterQualityResponse
import acal.com.core.application.waterQuality.data.out.response
import acal.com.core.domain.usecase.waterquality.WaterQualityCreateUseCase
import org.springframework.http.HttpStatus.CREATED
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(
    value = ["/water-quality"],
)
class WaterQualityController(
    private val create: WaterQualityCreateUseCase,
) {

    @PostMapping
    @ResponseStatus(CREATED)
    fun create(@RequestBody request: WaterQualityRequest): WaterQualityResponse =
        create.execute(request.toDomain()).response()

}
