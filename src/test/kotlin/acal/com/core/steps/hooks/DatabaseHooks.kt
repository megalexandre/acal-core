package acal.com.core.steps.hooks

import io.cucumber.java.Before
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate

class DatabaseHooks {

    @Autowired
    private lateinit var mongoTemplate: MongoTemplate

    @Before
    fun limparBancoDeDados() {
        mongoTemplate.collectionNames.forEach { it ->
            if (!it.startsWith("system.")) {
                mongoTemplate.dropCollection(it)
            }
        }
    }
}