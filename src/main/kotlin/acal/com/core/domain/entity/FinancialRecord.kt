package acal.com.core.domain.entity

import acal.com.core.domain.enums.FinancialRecordReason
import java.math.BigDecimal
import java.time.LocalDateTime

class FinancialRecord(
    val id: String,
    val createdAt: LocalDateTime,
    val createdBy: String,
    val detail: FinancialRecordDetail
)

class FinancialRecordDetail(
    val number: String,
    val total: BigDecimal,
    val reason: FinancialRecordReason
)

