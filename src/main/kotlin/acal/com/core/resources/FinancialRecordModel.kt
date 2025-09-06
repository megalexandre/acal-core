package acal.com.core.resources

import acal.com.core.domain.entity.FinancialRecord
import acal.com.core.domain.entity.FinancialRecordDetail
import acal.com.core.domain.enums.FinancialRecordReason
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.time.LocalDateTime

@Document("financial_record")
data class FinancialRecordModel(
    @Id
    val id: String,
    val createdAt: LocalDateTime,
    val createdBy: String,
    val itemId: String,
    val number: String,
    val total: BigDecimal,
    val reason: String,
)

fun FinancialRecordModel.toDomain(): FinancialRecord = FinancialRecord(
    id = id,
    createdAt = createdAt,
    createdBy = createdBy,
    detail = FinancialRecordDetail(
      id = itemId,
      number = number,
      total = total,
        reason = FinancialRecordReason.valueOf(reason)
    ),
)

fun FinancialRecord.toEntity(): FinancialRecordModel = FinancialRecordModel(
    id = id,
    createdAt = createdAt,
    createdBy = createdBy,
    number = detail.number,
    total = detail.total,
    reason = detail.reason.name,
    itemId = detail.id
)

