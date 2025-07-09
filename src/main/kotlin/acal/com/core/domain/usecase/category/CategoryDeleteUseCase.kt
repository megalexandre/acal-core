package acal.com.core.domain.usecase.category

import acal.com.core.domain.datasource.CategoryDataSource
import org.springframework.stereotype.Component

@Component
class CategoryDeleteUseCase(
    private val categoryDataSource: CategoryDataSource
) {
    fun execute(id: String) {
        categoryDataSource.deleteById(id = id)
    }
}
