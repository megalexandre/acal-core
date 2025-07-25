package acal.com.core.configuration

import com.mongodb.client.MongoClients
import jakarta.annotation.PostConstruct
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory
import org.testcontainers.containers.MongoDBContainer
import java.io.File
import java.time.Duration

@TestConfiguration(proxyBeanMethods = false)
class TestContainersConfig {

    companion object {
        val mongoDBContainer = MongoDBContainer("mongo:8.0")
            .apply {
                withReuse(true)
                start()
            }
    }

    @Bean
    fun mongoTemplate(): MongoTemplate =
        MongoTemplate(SimpleMongoClientDatabaseFactory(
            MongoClients.create(mongoDBContainer.getReplicaSetUrl("acal")),
            "acal"
        ))

}
