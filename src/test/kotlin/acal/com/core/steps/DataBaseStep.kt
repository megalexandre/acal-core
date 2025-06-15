package acal.com.core.steps

import io.cucumber.java.pt.Dado
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.test.context.ContextConfiguration
import acal.com.core.configuration.CucumberSpringConfiguration

@ContextConfiguration(classes = [CucumberSpringConfiguration::class])
class DataBaseStep {

    @Autowired
    private lateinit var mongoTemplate: MongoTemplate

    @Dado("o banco de dados está vazio")
    fun `o banco de dados esta vazio`() {
        mongoTemplate.db.drop()
    }

}