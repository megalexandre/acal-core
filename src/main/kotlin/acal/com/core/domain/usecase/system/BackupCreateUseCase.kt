package acal.com.core.domain.usecase.system

import acal.com.core.infrastructure.database.Database
import org.springframework.stereotype.Service

@Service
class BackupCreateUseCase(
    private val database: Database
) {

    fun execute(): ByteArray {
        return database.backupMongo()
    }
}