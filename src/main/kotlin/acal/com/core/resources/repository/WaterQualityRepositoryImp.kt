package acal.com.core.resources.repository

import acal.com.core.domain.datasource.WaterQualityDataSource
import acal.com.core.domain.entity.Reference
import acal.com.core.domain.entity.WaterQuality
import acal.com.core.resources.WaterQualityModel
import acal.com.core.resources.toDomain
import acal.com.core.resources.toEntity
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
class WaterQualityRepositoryImp(
    private val repository: WaterQualityRepository,
): WaterQualityDataSource {

    override fun findAll(): Collection<WaterQuality> {
        return repository.findAll(
            Sort.by(
                Sort.Order.desc("reference"),
            )
        ).map { entity ->
            val domain = entity.toDomain()
            domain.copy(
                analysis = domain.analysis.sortedBy { it.name }
            )
        }
    }

    override fun findById(id: String): WaterQuality? {
        return repository.findById(id).orElse(null)?.toDomain()
    }

    override fun save(t: WaterQuality): WaterQuality {
        return repository.save(t.toEntity()).toDomain()
    }

    override fun save(t: Collection<WaterQuality>): Collection<WaterQuality> {
        return repository.saveAll(t.map { it.toEntity() }).map { it.toDomain() }
    }

    override fun update(t: WaterQuality): WaterQuality {
        return repository.save(t.toEntity()).toDomain()
    }

    override fun deleteById(id: String) {
        repository.deleteById(id)
    }

    override fun findByReferences(references: Collection<Reference>): Collection<WaterQuality> {
        return repository.findByReferenceIn(references.map { it.toString() }).map { it.toDomain() }
    }
}

interface WaterQualityRepository: MongoRepository<WaterQualityModel, String>{
     fun findByReferenceIn(references: Collection<String>): Collection<WaterQualityModel>
}
