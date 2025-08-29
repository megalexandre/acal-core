package acal.com.core.domain.datasource

import acal.com.core.domain.entity.Invoice
import acal.com.core.domain.entity.Reference
import acal.com.core.domain.valueobject.InvoiceFilter
import org.springframework.data.domain.Page

interface InvoiceDataSource : DefaultDataSource<Invoice>{
    fun pay(id: String)
    fun cancelPayment(id: String)
    fun delete(id: String)
    fun paginate(filter: InvoiceFilter): Page<Invoice>
    fun findAll(filter: InvoiceFilter): Collection<Invoice>
    fun countByReferencesContaining(reference: Reference): Long
}
