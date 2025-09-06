package acal.com.core.domain.usecase.financialrecord

import acal.com.core.comons.Id
import acal.com.core.domain.datasource.FinancialRecordDataSource
import acal.com.core.domain.entity.FinancialRecord
import acal.com.core.domain.entity.FinancialRecordDetail
import acal.com.core.domain.enums.FinancialRecordReason
import acal.com.core.domain.valueobject.FinancialRecordCreate
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class FinancialRecordCreateUseCase(
    private val dataSource: FinancialRecordDataSource
) {
    fun execute(item: FinancialRecordCreate){
        dataSource.save(FinancialRecord(
            id = Id.random(),
            createdAt = LocalDateTime.now(),
            createdBy = "system",
            detail = build(item)
        ))
    }

    private fun build(item: FinancialRecordCreate): FinancialRecordDetail =
        FinancialRecordDetail(
            id = item.financialRecord.id,
            number = item.financialRecord.number,
            reason = item.reason,
            total = when(item.reason){
                FinancialRecordReason.INVOICE_PAYMENT -> item.financialRecord.total
                else -> item.financialRecord.total.negate()
            }
        )
}

