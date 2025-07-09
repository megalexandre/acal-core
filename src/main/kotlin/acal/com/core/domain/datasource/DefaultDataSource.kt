package acal.com.core.domain.datasource

interface DefaultDataSource<T> {

    fun findAll(): Collection<T>
    fun findById(id: String): T?
    fun save(t: T): T
    fun saveAll(t: Collection<T>): Collection<T>
    fun update(t: T): T
    fun save(t: Collection<T>): Collection<T>
    fun deleteById(id: String)

}