package acal.com.core.resources.repository

import acal.com.core.domain.datasource.CategoryDataSource
import acal.com.core.domain.entity.Category
import acal.com.core.infrastructure.event.CategoryEvent
import acal.com.core.infrastructure.event.EventType
import acal.com.core.resources.CategoryModel
import acal.com.core.resources.toDomain
import acal.com.core.resources.toEntity
import org.springframework.context.ApplicationEventPublisher
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
class CategoryRepositoryImp(
    private val categoryRepository: CategoryRepository,
    private val publisher: ApplicationEventPublisher
): CategoryDataSource {

    override fun save(t: Category): Category =
        categoryRepository.save(t.toEntity()).toDomain()

    override fun update(t: Category): Category {
        return save(t).also {
            publisher.publishEvent(CategoryEvent(eventType = EventType.UPDATE, category = it))
        }
    }

    override fun save(t: Collection<Category>): Collection<Category> {
        return categoryRepository.saveAll(t.map { it.toEntity() }).map { it.toDomain() }
    }

    override fun findById(id: String): Category? =
        categoryRepository.findById(id).orElse(null)?.toDomain()

    override fun findAll(): Collection<Category> =
        categoryRepository.findAll(
            Sort.by(
                Sort.Order.asc("group"),
                Sort.Order.asc("name"),
            )
        ).map { it.toDomain() }


    override fun deleteById(id: String) =
        categoryRepository.deleteById(id)
}

interface CategoryRepository: MongoRepository<CategoryModel, String>
