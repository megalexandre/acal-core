package acal.com.core.application.financialregister.data.out

import acal.com.core.comons.currency
import acal.com.core.comons.currentDate
import acal.com.core.domain.entity.FinancialRecord

class FinancialRecordResponsePDF (
    val id: String,
    val createdAt: String,
    val createdBy: String,
    val number: String,
    val total: String,
    val reason: String
    )

fun FinancialRecord.toPDF(): FinancialRecordResponsePDF = FinancialRecordResponsePDF(
    id = id,
    createdAt = createdAt.currentDate(),
    createdBy = createdBy,
    number = detail.number,
    total = detail.total.currency(),
    reason = detail.reason.description
)

fun Collection<FinancialRecord>.toPdf(): Collection<FinancialRecordResponsePDF> = this.map { it.toPDF() }