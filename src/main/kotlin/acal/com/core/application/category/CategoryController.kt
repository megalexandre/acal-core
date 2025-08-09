package acal.com.core.application.category

import acal.com.core.application.category.data.`in`.CategoryCreateRequest
import acal.com.core.application.category.data.`in`.CategoryUpdateRequest
import acal.com.core.application.category.data.out.CategoryResponse
import acal.com.core.application.category.data.out.categoryResponse
import acal.com.core.domain.datasource.CategoryDataSource
import acal.com.core.domain.usecase.category.*
import acal.com.core.infrastructure.exception.DataNotFoundException
import jakarta.validation.Valid
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(
    value = ["/category"],
)
class CategoryController(
    private val create: CategoryCreateUseCase,
    private val delete: CategoryDeleteUseCase,
    private val saveAll: CategoryCreateAllUseCase,
    private val update: CategoryUpdateUseCase,
    private val findById: CategoryByIdUseCase,
    private val dataSource: CategoryDataSource,
) {

    @PostMapping
    @ResponseStatus(CREATED)
    fun create(@RequestBody request: CategoryCreateRequest): CategoryResponse =
        create.execute(request.toDomain()).categoryResponse()

    @PostMapping("/all")
    @ResponseStatus(CREATED)
    fun create(@RequestBody request: List<CategoryCreateRequest>)=
        saveAll.execute(request.map { it.toDomain() })

    @DeleteMapping("/{id}")
    @ResponseStatus(OK)
    fun delete(@PathVariable id: String) =
        delete.execute(id)

    @PutMapping
    @ResponseStatus(OK)
    fun update(@RequestBody @Valid request: CategoryUpdateRequest): CategoryResponse =
        update.execute(request.toDomain()).categoryResponse()

    @GetMapping
    @ResponseStatus(OK)
    fun get(): Collection<CategoryResponse> =
        dataSource.findAll().categoryResponse()

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    fun getById(@PathVariable id: String): CategoryResponse? =
        findById.execute(id)?.categoryResponse()
            ?: throw DataNotFoundException("Categoria não encontrada com o ID: $id")
}
