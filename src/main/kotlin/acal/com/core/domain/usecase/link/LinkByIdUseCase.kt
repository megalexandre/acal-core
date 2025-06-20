package acal.com.core.domain.usecase.link

import acal.com.core.domain.datasource.LinkDataSource
import acal.com.core.domain.entity.Link
import org.springframework.stereotype.Component

@Component
class LinkByIdUseCase(
    private val dataSource: LinkDataSource
) {
    fun execute(id: String): Link? =
        dataSource.findById(id)

}
