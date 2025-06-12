package acal.com.core.config

import acal.com.core.CoreApplication
import io.cucumber.spring.CucumberContextConfiguration
import org.slf4j.LoggerFactory
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.context.annotation.Import

@CucumberContextConfiguration
@SpringBootTest(
    webEnvironment = RANDOM_PORT,
    classes = [CoreApplication::class]
)
@Import(MongoTestContainerConfig::class)
class CucumberSpringConfig {

    private val logger = LoggerFactory.getLogger(CucumberSpringConfig::class.java)

    init {
        logger.info("MongoDB URI: ${System.getProperty("spring.data.mongodb.uri")}")
    }

}