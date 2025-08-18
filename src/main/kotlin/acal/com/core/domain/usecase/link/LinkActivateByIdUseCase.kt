package acal.com.core.domain.usecase.link

import acal.com.core.domain.datasource.LinkDataSource
import org.springframework.stereotype.Component

@Component
class LinkActivateByIdUseCase(
    private val dataSource: LinkDataSource
) {
    fun execute(id: String) =
        dataSource.activate(id)

}
