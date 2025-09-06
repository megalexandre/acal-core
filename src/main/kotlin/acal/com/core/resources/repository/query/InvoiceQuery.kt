package acal.com.core.resources.repository.query

import acal.com.core.domain.valueobject.InvoiceFilter
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query

class InvoiceQuery {

    fun query(filter: InvoiceFilter): Query {
        val criteria = Criteria()

        when {
            filter.paid -> criteria.and("paid_at").ne(null)
            filter.notPaid -> criteria.and("paid_at").`is`(null)
        }

        filter.number?.takeIf { it.isNotBlank() }?.let {
            criteria.and("number").regex(it.trim())
        }

        filter.address?.let {
            criteria.and("place.address.id").`is`(it.id)
        }

        val query = Query(criteria)

        val sort = filter.sortOrders?.takeIf { it.isNotEmpty() }?.let {
            Sort.by(it.map { order ->
                Sort.Order(Sort.Direction.fromString(order.direction), order.property)
            })
        } ?: Sort.by(Sort.Direction.DESC, "id")

        query.with(sort)

        return query
    }

}