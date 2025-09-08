package acal.com.core.application.waterMeter

import acal.com.core.application.waterMeter.data.`in`.WaterMeterCreateRequest
import acal.com.core.domain.entity.WaterMeter
import acal.com.core.domain.usecase.watermeter.WaterMeterCreateUseCase
import acal.com.core.domain.usecase.watermeter.WaterMeterDeleteUseCase
import acal.com.core.domain.usecase.watermeter.WaterMeterPaginateUseCase
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

    ) {

    @PostMapping("/paginate")
    @ResponseStatus(HttpStatus.OK)
    fun paginate(@RequestBody filter: WaterMeterFilter): Page<WaterMeter> =
        paginate.execute(filter)

    @PostMapping
    @ResponseStatus(CREATED)
    fun create(@RequestBody request: WaterMeterCreateRequest){
        create.execute(request.toDomain())
    }

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
