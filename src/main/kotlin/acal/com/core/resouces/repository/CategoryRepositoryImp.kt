package acal.com.core.resouces.repository

import acal.com.core.domain.datasource.CategoryDataSource
import acal.com.core.domain.entity.Category
import acal.com.core.resouces.CategoryModel
import acal.com.core.resouces.toDomain
import acal.com.core.resouces.toEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
class CategoryRepositoryImp(
    val categoryRepository: CategoryRepository
): CategoryDataSource {

    override fun save(category: Category): Category =
        categoryRepository.save(category.toEntity()).toDomain()

    override fun saveAll(categories: Collection<Category>): Collection<Category> =
        categoryRepository.saveAll(categories.map { it.toEntity() }).map { it.toDomain() }

    override fun findById(id: String): Category? =
        categoryRepository.findById(id).orElse(null)?.toDomain()

    override fun findAll(): Collection<Category> =
        categoryRepository.findAll().map { it.toDomain() }

    override fun deleteById(id: String) =
        categoryRepository.deleteById(id)
}

interface CategoryRepository: MongoRepository<CategoryModel, String>
