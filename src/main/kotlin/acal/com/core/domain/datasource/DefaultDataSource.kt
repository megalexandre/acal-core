package acal.com.core.domain.datasource

interface DefaultDataSource<T> {

    fun findAll(): Collection<T>
    fun save(t: T): T
    fun save(t: Collection<T>): Collection<T>

}