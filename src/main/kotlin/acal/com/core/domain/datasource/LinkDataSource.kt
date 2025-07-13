package acal.com.core.domain.datasource

import acal.com.core.domain.entity.Link
import acal.com.core.domain.valueobject.LinkFilter
import org.springframework.data.domain.Page

interface LinkDataSource {

    fun save(t: Collection<Link>): Collection<Link>
    fun save(t: Link): Link
    fun deleteById(id: String)
    fun findById(id: String): Link?
    fun paginate(filter: LinkFilter): Page<Link>
}
