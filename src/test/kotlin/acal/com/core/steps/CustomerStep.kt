package acal.com.core.steps

import acal.com.core.configuration.CucumberSpringConfiguration
import acal.com.core.resouces.CustomerModel
import acal.com.core.resouces.repository.CustomerRepository
import io.cucumber.java.pt.Dado
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
}