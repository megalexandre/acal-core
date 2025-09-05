package acal.com.core.domain.usecase.link

import acal.com.core.domain.entity.LinkNumber
import acal.com.core.resources.repository.SequenceRepositoryImpl
import org.springframework.stereotype.Service
import java.time.Year

@Service
class LinkNumberCreateUseCase (
    private val sequenceRepository: SequenceRepositoryImpl
){

    fun create(): LinkNumber {
        val year = Year.now()
        val sequence = sequenceRepository.generateSequence("invoice_number_$year")
            .toString().padStart(6, '0')

        return LinkNumber(
            year = year,
            sequence
         )
    }

}