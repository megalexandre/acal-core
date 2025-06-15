package acal.com.core.steps

import acal.com.core.configuration.CucumberSpringConfiguration
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import io.cucumber.java.Before
import io.cucumber.java.pt.E
import io.cucumber.java.pt.Entao
import io.cucumber.java.pt.Quando
import io.restassured.RestAssured
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.context.ContextConfiguration

@ContextConfiguration(classes = [CucumberSpringConfiguration::class])
class RestStep {

    @LocalServerPort
    private var port: Int = 0

    @Autowired
    private lateinit var sharedContext: SharedContext

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Before
    fun setup() {
        RestAssured.baseURI = "http://localhost"
        RestAssured.port = port
    }

    @Quando("eu envio um POST para {string}")
    fun `eu envio um POST para`(endpoint: String, corpo: String) {
        sharedContext.response = RestAssured.given()
            .contentType("application/json")
            .body(corpo)
            .post(endpoint)
    }

    @Quando("eu envio um GET para {string}")
    fun `eu envio um GET para`(path: String) {
        sharedContext.response = RestAssured.given()
            .contentType("application/json")
            .get(path)
    }

    @Entao("o código da resposta deve ser {int}")
    fun `o código da resposta deve ser`(status: Int) {
        assertEquals(status, sharedContext.response?.statusCode)
    }

    @E("o corpo da resposta deve conter")
    fun `o corpo da resposta deve conter`(expectedBody: String) {
        val actualBody = sharedContext.response?.body?.asString()
        val expectedJson = objectMapper.readTree(expectedBody)
        val actualJson = objectMapper.readTree(actualBody)

        assertEquals(expectedJson, actualJson)
    }

    @E("o corpo da resposta deve conter as chaves")
    fun `o corpo da resposta deve conter as chaves`(expectedKeysJson: String) {
        val actualBody = sharedContext.response?.body?.asString()
        val expectedJson = objectMapper.readTree(expectedKeysJson)
        val actualJson = objectMapper.readTree(actualBody)

        if (expectedJson.isArray()) {
            // Verifica se ambos são arrays
            assertTrue(actualJson.isArray(), "A resposta não é um array como esperado")

            // Para cada item no array esperado, verifica se há um item correspondente no array real
            expectedJson.forEachIndexed { index, expectedItem ->
                if (index < actualJson.size()) {
                    val actualItem = actualJson[index]
                    verifyKeysExist(expectedItem, actualItem)
                } else {
                    assertTrue(false, "O array da resposta não tem elementos suficientes")
                }
            }
        } else {
            // Se não for array, verifica diretamente
            verifyKeysExist(expectedJson, actualJson)
        }
    }

    private fun verifyKeysExist(expected: JsonNode, actual: JsonNode) {
        expected.fieldNames().forEach { fieldName ->
            assertTrue(actual.has(fieldName), "Campo '$fieldName' não encontrado na resposta")

            // Se o campo esperado for um objeto, verifica recursivamente
            if (expected.get(fieldName).isObject) {
                verifyKeysExist(expected.get(fieldName), actual.get(fieldName))
            }
        }
    }
}
