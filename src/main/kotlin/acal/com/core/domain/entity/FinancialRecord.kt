package acal.com.core.domain.entity

import java.math.BigDecimal
import java.time.LocalDateTime

class FinancialRecord(
    val id: String,
    val createdAt: LocalDateTime,
    val createdBy: String,
    val source: FinancialRecordSummary
){
    val total: BigDecimal
        get() = source.total
}

class FinancialRecordSummary(
    val id: String,
    val number: String,
    val total: BigDecimal,
)
