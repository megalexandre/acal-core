package acal.com.core.infrastructure.database

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Component
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

@Component
class Database(
    @Autowired private val mongoTemplate: MongoTemplate,
    @Value("\${spring.data.mongodb.uri}") private val mongoUri: String,
) {

    private val objectMapper = ObjectMapper().registerKotlinModule()

    @Throws(IOException::class)
    fun backupMongo(): ByteArray {
        try {
            val backupData = createBackupData()
            val indexesData = createIndexesData()
            return createZipFromBackupData(backupData, indexesData)
        } catch (e: Exception) {
            throw RuntimeException("Erro ao criar backup do banco de dados: ${e.message}", e)
        }
    }

    private fun createBackupData(): Map<String, Any> {
        val backupData = mutableMapOf<String, Any>()

        try {
            val collections: Set<String> = mongoTemplate.collectionNames

            collections.forEach { collectionName ->
                val documents: List<*> = mongoTemplate.findAll(Map::class.java, collectionName)
                backupData[collectionName] = documents
            }

            backupData["backup_metadata"] = mapOf(
                "created_at" to System.currentTimeMillis(),
                "version" to "1.0",
                "total_collections" to collections.size,
                "mongodb_uri" to parseMongoUri(mongoUri).let { "${it.first}:${it.second}/${it.third}" }
            )

            return backupData

        } catch (e: Exception) {
            throw RuntimeException("Erro ao obter dados do MongoDB: ${e.message}", e)
        }
    }

    private fun createIndexesData(): Map<String, Any> {
        val indexesData = mutableMapOf<String, Any>()

        try {
            val collections: Set<String> = mongoTemplate.collectionNames

            collections.forEach { collectionName ->
                try {
                    val collection = mongoTemplate.getCollection(collectionName)
                    val indexes: List<*>  = collection.listIndexes().into(mutableListOf())
                    indexesData[collectionName] = indexes
                } catch (e: Exception) {
                    println("Aviso: Não foi possível obter índices da collection '$collectionName': ${e.message}")
                    indexesData[collectionName] = emptyList<Any>()
                }
            }

            return indexesData

        } catch (e: Exception) {
            throw RuntimeException("Erro ao obter índices do MongoDB: ${e.message}", e)
        }
    }

    private fun createZipFromBackupData(backupData: Map<String, Any>, indexesData: Map<String, Any>): ByteArray {
        val byteArrayOutputStream = ByteArrayOutputStream()

        ZipOutputStream(byteArrayOutputStream).use { zipOut ->
            val mainJsonEntry = ZipEntry("backup_data.json")
            zipOut.putNextEntry(mainJsonEntry)
            zipOut.write(objectMapper.writeValueAsBytes(backupData))
            zipOut.closeEntry()

            backupData.forEach { (collectionName, data) ->
                if (collectionName != "backup_metadata") {
                    val collectionEntry = ZipEntry("collections/$collectionName.json")
                    zipOut.putNextEntry(collectionEntry)
                    zipOut.write(objectMapper.writeValueAsBytes(data))
                    zipOut.closeEntry()
                }
            }

            val allIndexesEntry = ZipEntry("indexes.json")
            zipOut.putNextEntry(allIndexesEntry)
            zipOut.write(objectMapper.writeValueAsBytes(indexesData))
            zipOut.closeEntry()

            indexesData.forEach { (collectionName, indexes) ->
                val indexEntry = ZipEntry("indexes/$collectionName.json")
                zipOut.putNextEntry(indexEntry)
                zipOut.write(objectMapper.writeValueAsBytes(indexes))
                zipOut.closeEntry()
            }

            val metadataEntry = ZipEntry("metadata.json")
            zipOut.putNextEntry(metadataEntry)
            zipOut.write(objectMapper.writeValueAsBytes(backupData["backup_metadata"]))
            zipOut.closeEntry()

            val restoreScriptEntry = ZipEntry("restore_script.js")
            zipOut.putNextEntry(restoreScriptEntry)
            zipOut.write(createRestoreScript(backupData, indexesData).toByteArray())
            zipOut.closeEntry()
        }

        return byteArrayOutputStream.toByteArray()
    }

    private fun createRestoreScript(backupData: Map<String, Any>, indexesData: Map<String, Any>): String {
        val script = StringBuilder()
        script.appendLine("// Script de restauração automática do MongoDB")
        script.appendLine("// Execute este script no mongo shell ou MongoDB Compass")
        script.appendLine()

        val (_, _, dbName) = parseMongoUri(mongoUri)
        script.appendLine("use $dbName;")
        script.appendLine()

        script.appendLine("// Remover collections existentes (descomente se necessário)")
        backupData.keys.filter { it != "backup_metadata" }.forEach { collectionName ->
            script.appendLine("// db.$collectionName.drop();")
        }
        script.appendLine()

        script.appendLine("// Recriar índices")
        indexesData.forEach { (collectionName, indexes) ->
            script.appendLine("// Índices para collection: $collectionName")
            if (indexes is List<*> && indexes.isNotEmpty()) {
                indexes.forEach { index ->
                    if (index is Map<*, *>) {
                        val name = index["name"] as? String
                        val key = index["key"]
                        if (name != "_id_" && key != null) {
                            script.appendLine("db.$collectionName.createIndex($key);")
                        }
                    }
                }
            }
            script.appendLine()
        }

        script.appendLine("print('Restauração de índices concluída!');")
        script.appendLine("print('Para restaurar os dados, importe os arquivos JSON das collections.');")
        script.appendLine("print('Exemplo: mongoimport --db $dbName --collection nomeCollection --file collections/nomeCollection.json --jsonArray');")

        return script.toString()
    }

    private fun parseMongoUri(uri: String): Triple<String, String, String> {
        val regex = Regex("mongodb://(?:[^@]+@)?([^:/]+):?(\\d+)?/([^?]+)")
        val matchResult = regex.find(uri)

        return if (matchResult != null) {
            val host = matchResult.groupValues[1]
            val port = matchResult.groupValues[2].ifEmpty { "27017" }
            val dbName = matchResult.groupValues[3]
            Triple(host, port, dbName)
        } else {
            Triple("localhost", "27017", "acal")
        }
    }
}