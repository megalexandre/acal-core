package acal.com.core.steps

import io.cucumber.java.Before
import io.cucumber.java.pt.Entao
import io.cucumber.java.pt.Quando
import io.restassured.RestAssured
import org.junit.jupiter.api.Assertions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.context.ContextConfiguration
import acal.com.core.configuration.CucumberSpringConfiguration

@ContextConfiguration(classes = [CucumberSpringConfiguration::class])
class RestStep {

    @LocalServerPort
    private var port: Int = 0

    @Autowired
    private lateinit var sharedContext: SharedContext

    @Before
    fun setup() {
        RestAssured.baseURI = "http://localhost"
        RestAssured.port = port
    }

    @Quando("eu envio um POST para {string}")
    fun envioPostPara(endpoint: String, corpo: String) {
        sharedContext.response = RestAssured.given()
            .contentType("application/json")
            .body(corpo)
            .post(endpoint)
    }

    @Entao("a resposta deve ser {int}")
    fun respostaDeveSer(status: Int) {
        Assertions.assertEquals(status,sharedContext.response?.statusCode )
    }
}
