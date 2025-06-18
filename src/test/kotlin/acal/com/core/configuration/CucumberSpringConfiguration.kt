package acal.com.core.configuration

import acal.com.core.CoreApplication
import acal.com.core.infrastructure.config.MongoConfiguration
import io.cucumber.spring.CucumberContextConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.context.annotation.Import
import org.springframework.test.context.ContextConfiguration

@SpringBootTest(
    webEnvironment = RANDOM_PORT,
    classes = [CoreApplication::class]
)
@CucumberContextConfiguration
@ContextConfiguration(classes = [TestContainersConfig::class])
@Import(MongoConfiguration::class)
class CucumberSpringConfiguration