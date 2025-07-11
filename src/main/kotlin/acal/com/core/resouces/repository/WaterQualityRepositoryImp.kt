package acal.com.core.resouces.repository

import acal.com.core.domain.datasource.WaterQualityDataSource
import acal.com.core.domain.entity.WaterQuality
import acal.com.core.resouces.WaterQualityModel
import acal.com.core.resouces.toDomain
import acal.com.core.resouces.toEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
class WaterQualityRepositoryImp(
    private val repository: WaterQualityRepository,
): WaterQualityDataSource {

    override fun findAll(): Collection<WaterQuality> {
        return repository.findAll().map { it.toDomain() }
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
}

interface WaterQualityRepository: MongoRepository<WaterQualityModel, String>
