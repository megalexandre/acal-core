package acal.com.core.domain.usecase.category

import acal.com.core.domain.datasource.CategoryDataSource
import acal.com.core.domain.entity.Category
import org.springframework.stereotype.Component

@Component
class CategoryCreateUseCase(
    val categoryDataSource: CategoryDataSource
) {
    fun execute(category: Category): Category =
        categoryDataSource.save(category)
}
