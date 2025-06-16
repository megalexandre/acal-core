package acal.com.core.infrastructure.config

import acal.com.core.infrastructure.serializer.LocalDateTimeDeserializer
import acal.com.core.infrastructure.serializer.LocalDateTimeSerializer
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SNAKE_CASE
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.kotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.LocalDateTime

@Configuration
class ObjectMapperConfiguration {

    @Bean
    fun objectMapper(): ObjectMapper =
        ObjectMapper().apply {
            propertyNamingStrategy = SNAKE_CASE
            registerModule(kotlinModule())
            registerModule(JavaTimeModule().apply {
                addSerializer(LocalDateTime::class.java, LocalDateTimeSerializer())
                addDeserializer(LocalDateTime::class.java, LocalDateTimeDeserializer())
            })
        }

}


