package acal.com.core.steps

import io.restassured.response.Response
import org.springframework.stereotype.Component

@Component
class SharedContext {
    var response: Response? = null
}

