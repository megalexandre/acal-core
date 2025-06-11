package acal.com.core.domain.datasource

interface DefaultDataSource<T> {

    fun findAll(): List<T>
    fun save(t: T): T

}