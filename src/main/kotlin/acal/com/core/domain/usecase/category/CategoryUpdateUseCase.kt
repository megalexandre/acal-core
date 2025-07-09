package acal.com.core.domain.usecase.category

import acal.com.core.domain.datasource.CategoryDataSource
import acal.com.core.domain.entity.Category
import org.springframework.stereotype.Component

@Component
class CategoryUpdateUseCase(
    val categoryDataSource: CategoryDataSource
) {
    fun execute(category: Category): Category =
        categoryDataSource.update(category)
}
