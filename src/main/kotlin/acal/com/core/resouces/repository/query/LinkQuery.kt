package acal.com.core.resouces.repository.query

import acal.com.core.domain.valueobject.LinkFilter
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query

class LinkQuery {

    fun query(filter: LinkFilter): Query {
        val criteria = Criteria()

        filter.name?.takeIf { it.isNotBlank() }?.let {
            criteria.and("customer.normalizedName").regex(it.trim(), "i")
        }

        filter.category?.takeIf { it.isNotBlank() }?.let {
            criteria.and("category.name").regex(it.trim(), "i")
        }

        filter.address?.takeIf { it.isNotBlank() }?.let {
            criteria.and("place.address").regex(it.trim(), "i")
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