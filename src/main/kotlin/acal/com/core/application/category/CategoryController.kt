package acal.com.core.application.category

import acal.com.core.application.category.data.`in`.CategoryCreateRequest
import acal.com.core.application.category.data.`in`.CategoryUpdateRequest
import acal.com.core.application.category.data.out.CategoryResponse
import acal.com.core.application.category.data.out.categoryResponse
import acal.com.core.domain.datasource.CategoryDataSource
import acal.com.core.domain.usecase.category.CategoryByIdUseCase
import acal.com.core.domain.usecase.category.CategoryUpdateUseCase
import acal.com.core.domain.usecase.category.CategoryCreateAllUseCase
import acal.com.core.domain.usecase.category.CategoryCreateUseCase
import acal.com.core.infrastructure.exception.DataNotFoundException
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(
    value = ["/category"],
)
class CategoryController(
    private val create: CategoryCreateUseCase,
    private val saveAll: CategoryCreateAllUseCase,
    private val update: CategoryUpdateUseCase,
    private val findById: CategoryByIdUseCase,
    private val dataSource: CategoryDataSource
) {

    @PostMapping
    @ResponseStatus(CREATED)
    fun create(@RequestBody request: CategoryCreateRequest): CategoryResponse =
        create.execute(request.toDomain()).categoryResponse()

    @PutMapping
    @ResponseStatus(OK)
    fun update(@RequestBody request: CategoryUpdateRequest): CategoryResponse =
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
