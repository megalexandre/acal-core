package acal.com.core.steps

import acal.com.core.configuration.CucumberSpringConfiguration
import acal.com.core.resouces.CategoryModel
import io.cucumber.java.pt.Dado
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import acal.com.core.resouces.repository.CategoryRepository
import io.cucumber.java.pt.Então
import io.micrometer.core.instrument.Counter
import org.junit.jupiter.api.Assertions

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
        Assertions.assertEquals(n, repository.count())

    }


}