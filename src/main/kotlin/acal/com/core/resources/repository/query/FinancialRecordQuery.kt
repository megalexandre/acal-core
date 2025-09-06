package acal.com.core.resources.repository.query

import acal.com.core.domain.valueobject.FinancialRecordFilter
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import java.time.LocalTime

class FinancialRecordQuery {

    fun query(filter: FinancialRecordFilter): Query {
        val criteria = Criteria()

        val query = Query(criteria)

        when {
            filter.dateStart != null && filter.dateEnd != null -> {
                criteria.and("created_at")
                    .gte(filter.dateStart.atStartOfDay())
                    .lte(filter.dateEnd.atTime(LocalTime.MAX))
            }
            filter.dateStart != null -> {
                criteria.and("created_at").gte(filter.dateStart)
            }
            filter.dateEnd != null -> {
                criteria.and("created_at").lte(filter.dateEnd)
            }
        }

        val sort = filter.sortOrders?.takeIf { it.isNotEmpty() }?.let {
            Sort.by(it.map { order ->
                Sort.Order(Sort.Direction.fromString(order.direction), order.property)
            })
        } ?: Sort.by(Sort.Direction.DESC, "id")

        query.with(sort)

        return query
    }

}