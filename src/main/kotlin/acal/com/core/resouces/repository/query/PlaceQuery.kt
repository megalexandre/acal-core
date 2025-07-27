package acal.com.core.resouces.repository.query

import acal.com.core.domain.valueobject.PlaceFilter
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query

class PlaceQuery {

    fun query(filter: PlaceFilter): Query {
        val criteria = Criteria()

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