package acal.com.core.application.link

import LinkResponse
import acal.com.core.application.link.data.`in`.LinkCreateRequest
import acal.com.core.domain.entity.Link
import acal.com.core.domain.usecase.link.LinkByIdUseCase
import acal.com.core.domain.usecase.link.LinkCreateUseCase
import linkResponse
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.CREATED
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(
    value = ["/link"],
)
class LinkController(
    private val linkCreateUseCase: LinkCreateUseCase,
    private val linkByIdUseCase: LinkByIdUseCase
) {

    @PostMapping
    @ResponseStatus(CREATED)
    fun create(@RequestBody request: LinkCreateRequest): Link =
        linkCreateUseCase.execute(request.toDomain())

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun get(@PathVariable id: String): LinkResponse? =
        linkByIdUseCase.execute(id)?.linkResponse()

}
