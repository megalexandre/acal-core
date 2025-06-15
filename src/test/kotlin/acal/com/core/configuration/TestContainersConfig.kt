package acal.com.core.configuration

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.testcontainers.containers.MongoDBContainer
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory
import com.mongodb.client.MongoClients

@TestConfiguration
class TestContainersConfig {

    companion object {
        val mongoDBContainer = MongoDBContainer("mongo:8.0")
            .apply { start() }
    }

    @Bean
    fun mongoTemplate(): MongoTemplate {
        val connectionString = mongoDBContainer.getReplicaSetUrl("acal")
        val mongoClientDatabaseFactory = SimpleMongoClientDatabaseFactory(
            MongoClients.create(connectionString),
            "acal"
        )
        return MongoTemplate(mongoClientDatabaseFactory)
    }
}
