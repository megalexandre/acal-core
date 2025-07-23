package acal.com.core.infrastructure.config

import acal.com.core.domain.entity.Reference
import acal.com.core.infrastructure.serializer.*
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SNAKE_CASE
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.kotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

@Configuration
class ObjectMapperConfiguration {

    @Bean
    fun objectMapper(): ObjectMapper =
        ObjectMapper().apply {
            propertyNamingStrategy = SNAKE_CASE
            configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

            registerModule(kotlinModule())
            registerModule(JavaTimeModule().apply {
                addSerializer(LocalDateTime::class.java, LocalDateTimeSerializer())
                addDeserializer(LocalDateTime::class.java, LocalDateTimeDeserializer())

                addSerializer(LocalDate::class.java, LocalDateSerializer())
                addDeserializer(LocalDate::class.java, LocalDateDeserializer())

                addSerializer(BigDecimal::class.java, BigDecimalSerializer())
                addDeserializer(BigDecimal::class.java, BigDecimalDeserializer())

                addSerializer(Reference::class.java, ReferenceSerializer())
                addDeserializer(Reference::class.java, ReferenceDeserializer())

            })
        }

}


