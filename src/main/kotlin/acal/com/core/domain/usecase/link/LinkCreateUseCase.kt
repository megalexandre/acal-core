package acal.com.core.domain.usecase.link

import acal.com.core.domain.datasource.LinkDataSource
import acal.com.core.domain.entity.Link
import acal.com.core.domain.entity.LinkNumber
import acal.com.core.domain.entity.Place
import acal.com.core.domain.usecase.category.CategoryByIdUseCase
import acal.com.core.domain.usecase.customer.CustomerByIdUseCase
import acal.com.core.domain.usecase.place.PlaceByIdUseCase
import acal.com.core.domain.valueobject.LinkCreate
import acal.com.core.infrastructure.exception.DataNotFoundException
import acal.com.core.infrastructure.exception.InvalidOperationException
import org.springframework.stereotype.Component

@Component
class LinkCreateUseCase(
    private val linkDataSource: LinkDataSource,
    private val customerByIdUseCase: CustomerByIdUseCase,
    private val placeByIdUseCase: PlaceByIdUseCase,
    private val categoryByIdUseCase: CategoryByIdUseCase,
    private val linkNumberCreateUseCase: LinkNumberCreateUseCase
) {
    fun execute(linkCreate: LinkCreate): Link = with(linkCreate) {

        val place = placeByIdUseCase.execute(placeId) ?:
            throw DataNotFoundException("place not found: $placeId")

        //canSave(place)

        val customer = customerByIdUseCase.execute(customerId) ?:
            throw DataNotFoundException("customer not found: $customerId")

        val category = categoryByIdUseCase.execute(categoryId) ?:
            throw DataNotFoundException("category not found: $categoryId")

        val linkNumber: LinkNumber = linkNumberCreateUseCase.create()

        val link = Link(
            id = id,
            number = linkNumber.number,
            customer = customer,
            place = place,
            category = category,
            exclusiveMember = linkCreate.exclusiveMember,
            active = linkCreate.active,
            references = null,
        )


        linkDataSource.save(link)
    }

    fun canSave(place: Place) {

        val place = linkDataSource.findActiveLinkByPlace(place)

        place?.let {
            throw InvalidOperationException( "PLACE ERROR")
        }
    }
}
