package acal.com.core.domain.usecase.financialrecord

import acal.com.core.domain.datasource.FinancialRecordDataSource
import acal.com.core.domain.entity.FinancialRecord
import acal.com.core.domain.valueobject.FinancialRecordFilter
import org.springframework.data.domain.Page
import org.springframework.stereotype.Component

@Component
class FinancialRecordPaginateUseCase(
    private val dataSource: FinancialRecordDataSource,
) {
    fun execute(filter: FinancialRecordFilter): Page<FinancialRecord> {
        return dataSource.paginate(filter)
    }

}
