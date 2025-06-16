package acal.com.core.infrastructure.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mapping.model.SnakeCaseFieldNamingStrategy
import org.springframework.data.mongodb.core.convert.MongoCustomConversions
import org.springframework.data.mongodb.core.mapping.MongoMappingContext

@Configuration
class MongoConfiguration {

    @Bean
    fun mongoMappingContext(customConversions: MongoCustomConversions): MongoMappingContext =
        MongoMappingContext().apply {
            setFieldNamingStrategy(SnakeCaseFieldNamingStrategy())
            setSimpleTypeHolder(customConversions.simpleTypeHolder)
        }
}

