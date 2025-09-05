package acal.com.core.domain.datasource

import acal.com.core.domain.entity.FinancialRecord
import acal.com.core.domain.valueobject.FinancialRecordFilter
import org.springframework.data.domain.Page

interface FinancialRecordDataSource {
    fun save(financialRecord: FinancialRecord): FinancialRecord
    fun paginate(filter: FinancialRecordFilter): Page<FinancialRecord>
}
