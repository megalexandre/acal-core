package acal.com.core.resources.repository

import acal.com.core.domain.datasource.WaterMeterDataSource
import acal.com.core.domain.entity.Reference
import acal.com.core.domain.entity.WaterMeter
import acal.com.core.domain.valueobject.WaterMeterFilter
import acal.com.core.resources.WaterMeterModel
import acal.com.core.resources.repository.query.WaterMeterQuery
import acal.com.core.resources.toDomain
import acal.com.core.resources.toEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
class WaterMeterRepositoryImp(
    private val repository: WaterMeterRepository,
    private val mongoTemplate: MongoTemplate,
): WaterMeterDataSource {

    override fun findAll(reference: Reference): Collection<WaterMeter> =
        repository.findByReference(reference.toString()).map { it.toDomain() }

    override fun saveAll(waterMeter: Collection<WaterMeter>): Collection<WaterMeter> {
        return repository.saveAll(waterMeter.map { it.toEntity() }).map { it.toDomain() }
    }

    override fun save(waterMeter: WaterMeter): WaterMeter {
        return repository.save(waterMeter.toEntity()).toDomain()
    }

    override fun delete(id: String) {
        repository.deleteById(id)
    }

    override fun paginate(filter: WaterMeterFilter): Page<WaterMeter> {
        val query = WaterMeterQuery().query(filter)

        val pageable = PageRequest.of(filter.page, filter.size)

        val total = mongoTemplate.count(
            Query.of(query)
                .limit(-1)
                .skip(-1), WaterMeterModel::class.java)

        query.with(pageable)

        val content = mongoTemplate.find(query, WaterMeterModel::class.java).map { it.toDomain() }

        return PageImpl(content, pageable, total)
    }

}

interface WaterMeterRepository: MongoRepository<WaterMeterModel, String>{
     fun findByReference(reference: String): Collection<WaterMeterModel>
}
