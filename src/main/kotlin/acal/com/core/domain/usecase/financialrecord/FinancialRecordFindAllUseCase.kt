package acal.com.core.domain.usecase.financialrecord

import acal.com.core.domain.datasource.FinancialRecordDataSource
import acal.com.core.domain.entity.FinancialRecord
import acal.com.core.domain.valueobject.FinancialRecordFilter
import org.springframework.stereotype.Component

@Component
class FinancialRecordFindAllUseCase(
    private val dataSource: FinancialRecordDataSource,
) {
    fun execute(filter: FinancialRecordFilter): Collection<FinancialRecord> {
        return dataSource.findAll(filter)
    }

}
