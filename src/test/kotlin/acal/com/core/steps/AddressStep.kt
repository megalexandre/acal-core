package acal.com.core.steps

import acal.com.core.configuration.CucumberSpringConfiguration
import acal.com.core.resouces.AddressModel
import acal.com.core.resouces.repository.AddressRepository
import io.cucumber.java.pt.Dado
import org.junit.jupiter.api.Assertions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration

@ContextConfiguration(classes = [CucumberSpringConfiguration::class])
class AddressStep {

    @Autowired
    private lateinit var repository: AddressRepository

    @Dado("o endereço está cadastrado")
    fun `o endereço está cadastrado`(address: AddressModel) {
        repository.save(address)
    }

    @Dado("o banco de dados não deve conter o endereço")
    fun `o banco de dados não deve conter o endereço`() {
        Assertions.assertTrue { repository.findAll().isEmpty() }
    }
}