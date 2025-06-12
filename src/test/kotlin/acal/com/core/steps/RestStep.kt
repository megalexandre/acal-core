package acal.com.core.steps

import com.fasterxml.jackson.databind.ObjectMapper
import io.cucumber.java.Before
import io.cucumber.java.pt.Entao
import io.cucumber.java.pt.Quando
import io.restassured.RestAssured
import io.restassured.http.ContentType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.server.LocalServerPort

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
    fun enviarPostPara(path: String, body: String) {
        val response = RestAssured.given()
            .contentType(ContentType.JSON)
            .body(body)
            .post(path)
        sharedContext.response = response
    }

    @Entao("a resposta deve ser {int}")
    fun respostaDeveSer(status: Int) {
        val response = sharedContext.response
        requireNotNull(response) { "Nenhuma resposta HTTP encontrada no contexto compartilhado." }
        response.then().statusCode(status)
    }

}
