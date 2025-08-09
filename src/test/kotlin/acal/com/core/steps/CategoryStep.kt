package acal.com.core.steps

import acal.com.core.configuration.CucumberSpringConfiguration
import acal.com.core.resouces.CategoryModel
import acal.com.core.resouces.repository.CategoryRepository
import io.cucumber.java.pt.Dado
import io.cucumber.java.pt.Então
import org.junit.jupiter.api.Assertions.assertEquals
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration

@ContextConfiguration(classes = [CucumberSpringConfiguration::class])
class CategoryStep {

    @Autowired
    private lateinit var repository: CategoryRepository

    @Dado("a categoria está cadastrada")
    fun `a categoria está cadastrada`(category: CategoryModel) {
        repository.save(category)
    }

    @Então("o banco deve possuir {int} categorias")
    fun `o banco deve possuir n categorias`(n: Long) {
        assertEquals(n, repository.count())
    }


}