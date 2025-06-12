package acal.com.core.infrastructure.mongo

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.core.convert.MongoCustomConversions
import org.springframework.data.mongodb.core.mapping.MongoMappingContext
import org.springframework.data.mapping.model.SnakeCaseFieldNamingStrategy

@Configuration
class MongoConfig {

    @Bean
    fun mongoMappingContext(customConversions: MongoCustomConversions): MongoMappingContext {
        val mappingContext = MongoMappingContext()
        mappingContext.setFieldNamingStrategy(SnakeCaseFieldNamingStrategy())
        mappingContext.setSimpleTypeHolder(customConversions.simpleTypeHolder)
        return mappingContext
    }

}

