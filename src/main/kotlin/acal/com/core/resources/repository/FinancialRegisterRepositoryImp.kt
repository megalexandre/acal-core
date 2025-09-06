package acal.com.core.resources.repository

import acal.com.core.domain.datasource.FinancialRecordDataSource
import acal.com.core.domain.entity.FinancialRecord
import acal.com.core.domain.valueobject.FinancialRecordFilter
import acal.com.core.resources.FinancialRecordModel
import acal.com.core.resources.repository.query.FinancialRecordQuery
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
class FinancialRecordRepositoryImp(
    private val repository: FinancialRecordRepository,
    private val mongoTemplate: MongoTemplate,
): FinancialRecordDataSource {

    override fun save(financialRecord: FinancialRecord): FinancialRecord =
        repository.save(financialRecord.toEntity()).toDomain()

    override fun paginate(filter: FinancialRecordFilter): Page<FinancialRecord> {
        val query = FinancialRecordQuery().query(filter)

        val pageable = PageRequest.of(filter.page, filter.size)

        val total = mongoTemplate.count(
            Query.of(query)
                .limit(-1).skip(-1), FinancialRecordModel::class.java)

        query.with(pageable)

        val content = mongoTemplate.find(query, FinancialRecordModel::class.java).map { it.toDomain() }

        return PageImpl(content, pageable, total)
    }

    override fun findAll(filter: FinancialRecordFilter): Collection<FinancialRecord> {
        val query = FinancialRecordQuery().query(filter)
        return mongoTemplate.find(query, FinancialRecordModel::class.java).map { it.toDomain() }
    }

}

interface FinancialRecordRepository: MongoRepository<FinancialRecordModel, String>