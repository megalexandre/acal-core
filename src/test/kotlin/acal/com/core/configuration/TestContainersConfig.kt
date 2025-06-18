package acal.com.core.configuration

import com.mongodb.client.MongoClients
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory
import org.testcontainers.containers.MongoDBContainer

@TestConfiguration
class TestContainersConfig {

    companion object {
        val mongoDBContainer = MongoDBContainer("mongo:8.0")
            .apply { start() }
    }

    @Bean
    fun mongoTemplate(): MongoTemplate =
        MongoTemplate(SimpleMongoClientDatabaseFactory(
            MongoClients.create(mongoDBContainer.getReplicaSetUrl("acal")),
            "acal"
        ))

}
