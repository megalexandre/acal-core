package acal.com.core.application.financialregister.data.out

import acal.com.core.domain.entity.FinancialRecord
import org.springframework.data.domain.Page
import java.math.BigDecimal
import java.time.LocalDateTime

class FinancialRecordResponse (
    val id: String,
    val createdAt: LocalDateTime,
    val createdBy: String,
    val number: String,
    val total: BigDecimal,
    val reason: String
    )

fun FinancialRecord.toResponse(): FinancialRecordResponse = FinancialRecordResponse(
    id = id,
    createdAt = createdAt,
    createdBy = createdBy,
    number = detail.number,
    total = detail.total,
    reason = detail.reason.name
)

fun Page<FinancialRecord>.toPage(): Page<FinancialRecordResponse> = this.map { it.toResponse() }