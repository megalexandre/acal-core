package acal.com.core.domain.usecase.invoice

import acal.com.core.domain.datasource.InvoiceDataSource
import org.springframework.stereotype.Component

@Component
class InvoiceCancelPaymentUseCase(
    private val dataSource: InvoiceDataSource,
) {
    fun execute(id: String) {
        dataSource.cancelPayment(id)
    }

}
