package acal.com.core.domain.usecase.invoice

import acal.com.core.domain.datasource.InvoiceDataSource
import org.springframework.stereotype.Component

@Component
class InvoiceDeleteUseCase(
    private val dataSource: InvoiceDataSource,
) {
    fun execute(id: String) {
        dataSource.delete(id)
    }

}
