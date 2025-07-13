package acal.com.core.domain.usecase.link

import acal.com.core.domain.datasource.LinkDataSource
import acal.com.core.domain.entity.Link
import acal.com.core.domain.valueobject.LinkFilter
import org.springframework.data.domain.Page
import org.springframework.stereotype.Component

@Component
class LinkPaginateUseCase(
    private val dataSource: LinkDataSource
) {
    fun execute(linkFilter: LinkFilter): Page<Link> =
        dataSource.paginate(linkFilter)

}
