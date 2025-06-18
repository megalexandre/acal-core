package acal.com.core.infrastructure.config

import org.bson.Document
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Lazy
import org.springframework.context.event.EventListener
import org.springframework.data.mapping.model.SnakeCaseFieldNamingStrategy
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.convert.MongoCustomConversions
import org.springframework.data.mongodb.core.mapping.MongoMappingContext

@Configuration
class MongoConfiguration {

    private val logger = LoggerFactory.getLogger(MongoConfiguration::class.java)

    @Autowired
    @Lazy
    private lateinit var mongoTemplate: MongoTemplate

    @Bean
    fun mongoMappingContext(customConversions: MongoCustomConversions): MongoMappingContext =
        MongoMappingContext().apply {
            setFieldNamingStrategy(SnakeCaseFieldNamingStrategy())
            setSimpleTypeHolder(customConversions.simpleTypeHolder)
        }

    @EventListener(ApplicationReadyEvent::class)
    fun initIndexes() {
        createCustomerIndex()
        createAddressIndex()
        createPlaceIndex()
        createCategoryIndex()
    }

    private fun createCustomerIndex() {
        runCatching {
            val createIndexCommand = Document().apply {
                put("createIndexes", "customer")
                put("indexes", listOf(
                    Document().apply {
                        put("key", Document().apply {
                            put("identity_card", 1)
                        })
                        put("name", "unique_identity_index")
                        put("unique", true)
                    }
                ))
            }

            val result = mongoTemplate.executeCommand(createIndexCommand)
            logger.info("Resultado da criação do índice para customer: $result")
        }.onFailure {
            logger.error("Erro ao criar índice para customer", it)
        }
    }

    private fun createAddressIndex() {
        runCatching {
            val createIndexCommand = Document().apply {
                put("createIndexes", "address")
                put("indexes", listOf(
                    Document().apply {
                        put("key", Document().apply {
                            put("name", 1)
                        })
                        put("name", "unique_name_index")
                        put("unique", true)
                    }
                ))
            }

            val result = mongoTemplate.executeCommand(createIndexCommand)
            logger.info("Resultado da criação do índice para address: $result")
        }.onFailure {
            logger.error("Erro ao criar índice para address", it)
        }
    }

    private fun createPlaceIndex() {
        runCatching {
            val createIndexCommand = Document().apply {
                put("createIndexes", "places")
                put("indexes", listOf(
                    Document().apply {
                        put("key", Document().apply {
                            put("number", 1)
                            put("letter", 1)
                            put("address._id", 1)
                        })
                        put("name", "place_unique_idx")
                        put("unique", true)
                    }
                ))
            }

            val result = mongoTemplate.executeCommand(createIndexCommand)
            logger.info("Resultado da criação do índice para places: $result")
        }.onFailure {
            logger.error("Erro ao criar índice para places", it)
        }
    }

    private fun createCategoryIndex() {
        runCatching {
            val createIndexCommand = Document().apply {
                put("createIndexes", "category")
                put("indexes", listOf(
                    Document().apply {
                        put("key", Document().apply {
                            put("name", 1)
                            put("group", 1)
                        })
                        put("name", "unique_name_per_group_idx")
                        put("unique", true)
                    }
                ))
            }

            val result = mongoTemplate.executeCommand(createIndexCommand)
            logger.info("Resultado da criação do índice para category: $result")
        }.onFailure {
            logger.error("Erro ao criar índice para category", it)
        }
    }
}
