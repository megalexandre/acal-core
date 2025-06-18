package acal.com.core.domain.usecase.category

import acal.com.core.domain.datasource.CategoryDataSource
import acal.com.core.domain.entity.Category
import org.springframework.stereotype.Component

@Component
class CategoryCreateAllUseCase(
    val categoryDataSource: CategoryDataSource
) {
    fun execute(categories: Collection<Category>): Collection<Category> =
        categoryDataSource.saveAll(categories)
}
