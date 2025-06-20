package acal.com.core.domain.datasource

import acal.com.core.domain.entity.Link

interface LinkDataSource {
    fun save(link: Link): Link
    fun findById(id: String): Link?
}
