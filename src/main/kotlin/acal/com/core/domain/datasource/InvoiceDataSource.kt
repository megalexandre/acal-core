package acal.com.core.domain.datasource

import acal.com.core.domain.entity.Invoice
import acal.com.core.domain.valueobject.InvoiceFilter
import org.springframework.data.domain.Page

interface InvoiceDataSource : DefaultDataSource<Invoice>{
    fun paginate(filter: InvoiceFilter): Page<Invoice>
}
