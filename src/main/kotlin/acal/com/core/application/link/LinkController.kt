package acal.com.core.application.link

import LinkResponse
import acal.com.core.application.link.data.`in`.LinkCreateRequest
import acal.com.core.domain.entity.Link
import acal.com.core.domain.usecase.link.LinkByIdUseCase
import acal.com.core.domain.usecase.link.LinkCreateUseCase
import acal.com.core.domain.usecase.link.LinkPaginateUseCase
import acal.com.core.domain.valueobject.LinkFilter
import response
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.CREATED
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(
    value = ["/link"],
)
class LinkController(
    private val linkCreateUseCase: LinkCreateUseCase,
    private val linkByIdUseCase: LinkByIdUseCase,
    private val linkPaginateUseCase: LinkPaginateUseCase,
) {

    @PostMapping("/paginate")
    @ResponseStatus(HttpStatus.OK)
    fun get(@RequestBody filter: LinkFilter): Page<LinkResponse> =
        linkPaginateUseCase.execute(filter).response()

    @PostMapping
    @ResponseStatus(CREATED)
    fun create(@RequestBody request: LinkCreateRequest): LinkResponse =
        linkCreateUseCase.execute(request.toDomain()).response()

    @PostMapping("/all")
    @ResponseStatus(CREATED)
    fun create(@RequestBody request: Collection<LinkCreateRequest>): Collection<LinkResponse> =
        request.map { linkCreateUseCase.execute(it.toDomain())}.response()

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun get(@PathVariable id: String): LinkResponse? =
        linkByIdUseCase.execute(id)?.response()

}
