package acal.com.core.steps

import io.cucumber.java.pt.Dado
import org.springframework.test.context.ContextConfiguration
import acal.com.core.configuration.CucumberSpringConfiguration

@ContextConfiguration(classes = [CucumberSpringConfiguration::class])
class CustomerStep {

    @Dado("o cliente está registrado")
    fun `o cliente está registrado `() {
    }

}