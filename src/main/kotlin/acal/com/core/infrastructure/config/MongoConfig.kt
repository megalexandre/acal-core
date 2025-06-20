package acal.com.core.infrastructure.config

import acal.com.core.resouces.CustomerModel
import org.slf4j.LoggerFactory
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Lazy
import org.springframework.context.event.EventListener
import org.springframework.data.mapping.model.SnakeCaseFieldNamingStrategy
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.convert.MongoCustomConversions
import org.springframework.data.mongodb.core.index.MongoPersistentEntityIndexResolver
import org.springframework.data.mongodb.core.mapping.MongoMappingContext

@Configuration
class MongoConfiguration(@Lazy val mongoTemplate: MongoTemplate)  {

    private val logger = LoggerFactory.getLogger(MongoConfiguration::class.java)

    @EventListener(ApplicationReadyEvent::class)
    fun initIndexes() {
        val mappingContext = mongoTemplate.converter.mappingContext
        val indexResolver = MongoPersistentEntityIndexResolver(mappingContext)

        mappingContext.persistentEntities.forEach { entity ->
            indexResolver.resolveIndexFor(entity.typeInformation).forEach { indexDef ->
                mongoTemplate.indexOps(entity.type).createIndex(indexDef)
            }
        }
    }

    @Bean
    fun mongoMappingContext(customConversions: MongoCustomConversions): MongoMappingContext =
        MongoMappingContext().apply {
            setFieldNamingStrategy(SnakeCaseFieldNamingStrategy())
            setSimpleTypeHolder(customConversions.simpleTypeHolder)
        }

}
