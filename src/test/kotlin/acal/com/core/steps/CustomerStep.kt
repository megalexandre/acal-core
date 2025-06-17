package acal.com.core.steps

import acal.com.core.configuration.CucumberSpringConfiguration
import acal.com.core.resouces.CustomerModel
import acal.com.core.resouces.repository.CustomerRepository
import io.cucumber.java.pt.Dado
import io.cucumber.java.pt.E
import org.junit.jupiter.api.Assertions.assertEquals
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration

@ContextConfiguration(classes = [CucumberSpringConfiguration::class])
class CustomerStep {

    @Autowired
    private lateinit var customerRepository: CustomerRepository

    @Dado("o cliente está cadastrado")
    fun `o cliente está cadastrado`(customer: CustomerModel) {
        customerRepository.save(customer)
    }

    @Dado("os clientes estão cadastrados")
    fun `os clientes estão cadastrados`(customers: List<CustomerModel>) {
        customers.forEach { customer ->
            customerRepository.save(customer)
        }
    }

    @E("a base de dados deve conter um cliente")
    fun aBaseDeDadosDeveConterUmCliente(customer: CustomerModel) {
        customerRepository.findAll().first().apply {
            assertEquals(customer.name, this.name)
            assertEquals(customer.identityCard, this.identityCard)
            assertEquals(customer.phoneNumber, this.phoneNumber ?: "null")
            assertEquals(customer.partnerNumber, this.partnerNumber ?: "null")
            assertEquals(customer.voter, this.voter)
        }
    }
}