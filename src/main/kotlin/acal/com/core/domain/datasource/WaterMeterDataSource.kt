package acal.com.core.domain.datasource

import acal.com.core.domain.entity.Reference
import acal.com.core.domain.entity.WaterMeter
import acal.com.core.domain.valueobject.WaterMeterFilter
import org.springframework.data.domain.Page

interface WaterMeterDataSource {

    fun findAll(reference: Reference): Collection<WaterMeter>
    fun saveAll(waterMeter: Collection<WaterMeter>): Collection<WaterMeter>
    fun save(waterMeter: WaterMeter): WaterMeter
    fun delete(id: String)
    fun paginate(filter: WaterMeterFilter): Page<WaterMeter>
}
