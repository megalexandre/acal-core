package acal.com.core.application.waterMeter

import acal.com.core.application.waterMeter.data.`in`.WaterMeterCreateRequest
import acal.com.core.application.waterMeter.data.`in`.WaterMeterPreviewResponse
import acal.com.core.application.waterMeter.data.`in`.response
import acal.com.core.domain.entity.Reference
import acal.com.core.domain.entity.WaterMeter
import acal.com.core.domain.usecase.watermeter.WaterMeterCreateUseCase
import acal.com.core.domain.usecase.watermeter.WaterMeterDeleteUseCase
import acal.com.core.domain.usecase.watermeter.WaterMeterPaginateUseCase
import acal.com.core.domain.usecase.watermeter.WaterMeterPreviewUseCase
import acal.com.core.domain.valueobject.WaterMeterFilter
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(
    value = ["/water-meter"],
)
class WaterMeterController(
    private val create: WaterMeterCreateUseCase,
    private val paginate: WaterMeterPaginateUseCase,
    private val delete: WaterMeterDeleteUseCase,
    private val preView: WaterMeterPreviewUseCase

    ) {

    @GetMapping("/preview/{reference}")
    fun preview(@PathVariable reference: Reference): Collection<WaterMeterPreviewResponse> =
        preView.execute(reference).response()

    @PostMapping
    @ResponseStatus(CREATED)
    fun create(@RequestBody request: WaterMeterCreateRequest){
        create.execute(request.toDomain())
    }

    @PostMapping("/paginate")
    @ResponseStatus(HttpStatus.OK)
    fun paginate(@RequestBody filter: WaterMeterFilter): Page<WaterMeter> =
        paginate.execute(filter)

    @PostMapping("/all")
    @ResponseStatus(CREATED)
    fun create(@RequestBody request: Collection<WaterMeterCreateRequest>) {
        request.forEach { create.execute(it.toDomain()) }
    }

    @ResponseStatus(OK)
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String) {
        delete.execute(id)
    }
}
