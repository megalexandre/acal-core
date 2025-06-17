package acal.com.core.infrastructure.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Lazy
import org.springframework.context.event.EventListener
import org.springframework.data.domain.Sort
import org.springframework.data.mapping.model.SnakeCaseFieldNamingStrategy
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.convert.MongoCustomConversions
import org.springframework.data.mongodb.core.index.Index
import org.springframework.data.mongodb.core.mapping.MongoMappingContext
import org.slf4j.LoggerFactory

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
    fun initializeIndexes() {
        logger.info("Iniciando criação de índices MongoDB")

        try {
            // Criando índice único para identity_card na coleção customer
            createCustomerIndexes()

            // Adicione aqui a criação de índices para outras coleções

            logger.info("Índices MongoDB criados com sucesso")
        } catch (e: Exception) {
            logger.error("Erro ao criar índices MongoDB: ${e.message}", e)
        }
    }

    private fun createCustomerIndexes() {
        if (!mongoTemplate.collectionExists("customer")) {
            mongoTemplate.createCollection("customer")
            logger.info("Coleção 'customer' criada")
        }

        val indexOps = mongoTemplate.indexOps("customer")
        if (indexOps.indexInfo.any { it.name == "unique_card_index" }) {
            logger.info("Índice 'unique_card_index' já existe, nenhuma ação necessária")
        } else {
            val index = Index()
                .on("identity_card", Sort.Direction.ASC)
                .unique()
                .named("unique_card_index")

            indexOps.createIndex(index)
            logger.info("Índice único 'unique_card_index' criado para o campo 'identity_card'")
        }

        // Log dos índices existentes
        logger.info("Índices na coleção 'customer': ${indexOps.indexInfo.map { it.name }}")
    }
}
