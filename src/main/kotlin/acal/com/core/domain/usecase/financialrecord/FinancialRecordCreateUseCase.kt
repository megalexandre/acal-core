package acal.com.core.domain.usecase.financialrecord

import acal.com.core.comons.Id
import acal.com.core.domain.datasource.FinancialRecordDataSource
import acal.com.core.domain.entity.FinancialRecord
import acal.com.core.domain.entity.FinancialRecordDetail
import acal.com.core.domain.valueobject.FinancialRecordCreate
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class FinancialRecordCreateUseCase(
    private val dataSource: FinancialRecordDataSource
) {
    fun execute(item: FinancialRecordCreate) = with(item) {
        dataSource.save(FinancialRecord(
            id = Id.random(),
            createdAt = LocalDateTime.now(),
            createdBy = "system",
            detail = FinancialRecordDetail(
                number = financialRecord.id,
                reason = reason,
                total = financialRecord.total
            )
        ))
    }
}

