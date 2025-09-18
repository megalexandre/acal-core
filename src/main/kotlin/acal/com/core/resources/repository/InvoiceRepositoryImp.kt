package acal.com.core.resources.repository

import acal.com.core.domain.datasource.InvoiceDataSource
import acal.com.core.domain.entity.Invoice
import acal.com.core.domain.entity.Reference
import acal.com.core.domain.valueobject.InvoiceFilter
import acal.com.core.resources.InvoiceModel
import acal.com.core.resources.repository.query.InvoiceQuery
import acal.com.core.resources.toDomain
import acal.com.core.resources.toEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
class InvoiceRepositoryImp(
    private val repository: InvoiceRepository,
    private val mongoTemplate: MongoTemplate,
): InvoiceDataSource {

    override fun findAll(): Collection<Invoice> =
        repository.findAll().map { it.toDomain() }

    override fun save(t: Invoice): Invoice = repository.save(t.toEntity()).toDomain()

    override fun update(t: Invoice): Invoice {
       return save(t)
    }

    override fun save(t: Collection<Invoice>): Collection<Invoice> =
        repository.saveAll(t.map { it.toEntity() }).map { it.toDomain() }

    override fun deleteById(id: String) {
        repository.deleteById(id)
    }

    override fun delete(id: String) {
        repository.deleteById(id)
    }

    override fun findById(id: String): Invoice? =
        repository.findById(id).orElse(null)?.toDomain()

    override fun paginate(filter: InvoiceFilter): Page<Invoice> {
        val query = InvoiceQuery().query(filter)

        val pageable = PageRequest.of(filter.page, filter.size)

        val total = mongoTemplate.count(
            Query.of(query)
                .limit(-1).skip(-1), InvoiceModel::class.java)

        query.with(pageable)

        val content = mongoTemplate.find(query, InvoiceModel::class.java).map { it.toDomain() }

        return PageImpl(content, pageable, total)
    }

    override fun findAll(filter: InvoiceFilter): Collection<Invoice> {
        val query = InvoiceQuery().query(filter)
        return mongoTemplate.find(query, InvoiceModel::class.java).map { it.toDomain() }
    }

    override fun findByReference(reference: Reference): Collection<Invoice> {
        return repository.findByReference(reference.toString()).map { it.toDomain() }
    }

    override fun countByReferencesContaining(reference: Reference): Long {
        return repository.countByReferenceContaining(reference.toString())
    }

}

interface InvoiceRepository: MongoRepository<InvoiceModel, String>{
    fun countByReferenceContaining(reference: String): Long
    fun findByReference(reference: String): List<InvoiceModel>
}