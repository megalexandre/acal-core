package acal.com.core.steps

import acal.com.core.configuration.CucumberSpringConfiguration
import acal.com.core.resouces.CategoryModel
import io.cucumber.java.pt.Dado
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import acal.com.core.resouces.repository.CategoryRepository

@ContextConfiguration(classes = [CucumberSpringConfiguration::class])
class CategoryStep {

    @Autowired
    private lateinit var repository: CategoryRepository

    @Dado("a categoria está cadastrada")
    fun `a categoria está cadastrada`(category: CategoryModel) {
        repository.save(category)
    }

}