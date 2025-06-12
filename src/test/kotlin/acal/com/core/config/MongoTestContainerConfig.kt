package acal.com.core.config

import jakarta.annotation.PreDestroy
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.utility.DockerImageName

@TestConfiguration
class MongoTestContainerConfig {

    companion object {
        private val mongoDBContainer = MongoDBContainer(
            DockerImageName.parse("mongo:8.0"))
            .withExposedPorts(27017)
            .apply { start() }
    }


    @Bean
    fun mongoContainer(): MongoDBContainer {
        val uri = "mongodb://root:example@${mongoDBContainer.host}:${mongoDBContainer.getMappedPort(27017)}/acal"
        System.setProperty("spring.data.mongodb.uri", uri)
        return mongoDBContainer
    }

    @PreDestroy
    fun cleanup() {
        if (mongoDBContainer.isRunning) {
            mongoDBContainer.stop()
        }
    }
}

