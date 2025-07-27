package acal.com.core.application.link

import LinkResponse
import acal.com.core.application.link.data.`in`.LinkCreateRequest
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
    private val create: LinkCreateUseCase,
    private val findById: LinkByIdUseCase,
    private val paginate: LinkPaginateUseCase,
) {

    @PostMapping("/paginate")
    @ResponseStatus(HttpStatus.OK)
    fun get(@RequestBody filter: LinkFilter): Page<LinkResponse> =
        paginate.execute(filter).response()

    @PostMapping
    @ResponseStatus(CREATED)
    fun create(@RequestBody request: LinkCreateRequest): LinkResponse =
        create.execute(request.toDomain()).response()

    @PostMapping("/all")
    @ResponseStatus(CREATED)
    fun create(@RequestBody request: Collection<LinkCreateRequest>): Collection<LinkResponse> =
        request.map { create.execute(it.toDomain())}.response()

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun get(@PathVariable id: String): LinkResponse? =
        findById.execute(id)?.response()




}
