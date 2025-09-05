package acal.com.core.domain.entity

import java.math.BigDecimal

abstract class FinancialRecordItem(
    open val id: String,
    open val number: String,
    open val total: BigDecimal
)