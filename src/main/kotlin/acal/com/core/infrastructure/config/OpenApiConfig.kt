package acal.com.core.infrastructure.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import io.swagger.v3.oas.models.servers.Server
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {

    @Value("\${spring.application.name:ACAL Core API}")
    private lateinit var applicationName: String

    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .components(Components())
            .info(
                Info()
                    .title("$applicationName - Documentation")
                    .description("API documentation for ACAL Core services")
                    .version("1.0.0")
                    .contact(
                        Contact()
                            .name("ACAL Support Team")
                            .email("suporte@acal.com")
                    )
                    .license(
                        License()
                            .name("ACAL License")
                    )
            )
            .addServersItem(
                Server()
                    .url("/")
                    .description("Current environment")
            )
    }
}
