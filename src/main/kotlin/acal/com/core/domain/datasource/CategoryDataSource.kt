package acal.com.core.domain.datasource

import acal.com.core.domain.entity.Category

interface CategoryDataSource {
    fun save(category: Category): Category
    fun saveAll(categories: Collection<Category>): Collection<Category>
    fun findById(id: String): Category?
    fun deleteById(id: String)
}
