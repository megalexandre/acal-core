package acal.com.core.resouces.interfaces

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.repository.NoRepositoryBean
import java.time.LocalDateTime

@NoRepositoryBean
interface CustomRepository<T: DefaultModel >: MongoRepository<T, String> {

    fun findAllDeletedAtIsNull(): Collection<T>?

    fun softDeleteById(id: String) {
        findById(id).ifPresent {
            it.updatedAt = LocalDateTime.now()
            it.deletedAt = LocalDateTime.now()
            save(it)
        }
    }
}