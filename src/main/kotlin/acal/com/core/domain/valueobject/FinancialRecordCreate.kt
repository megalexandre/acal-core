package acal.com.core.domain.valueobject

import acal.com.core.domain.entity.FinancialRecordItem
import acal.com.core.domain.enums.FinancialRecordReason

class FinancialRecordCreate(
    val financialRecord: FinancialRecordItem,
    val reason: FinancialRecordReason
)