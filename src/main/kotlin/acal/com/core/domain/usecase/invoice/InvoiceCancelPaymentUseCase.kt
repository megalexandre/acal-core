package acal.com.core.domain.usecase.invoice

import acal.com.core.domain.datasource.InvoiceDataSource
import acal.com.core.domain.enums.FinancialRecordReason.INVOICE_REFUND
import acal.com.core.domain.usecase.financialrecord.FinancialRecordCreateUseCase
import acal.com.core.domain.valueobject.FinancialRecordCreate
import org.springframework.stereotype.Component

@Component
class InvoiceCancelPaymentUseCase(
    private val dataSource: InvoiceDataSource,
    private val financial: FinancialRecordCreateUseCase,
) {
    fun execute(id: String) {

        dataSource.findById(id)?.let {
            financial.execute( FinancialRecordCreate(financialRecord = it, reason = INVOICE_REFUND) )
            dataSource.save(it.copy(paidAt = null))
        }
    }

}
