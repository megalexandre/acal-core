package acal.com.core.application.financialregister

import acal.com.core.application.financialregister.data.out.FinancialRecordResponse
import acal.com.core.application.financialregister.data.out.toPage
import acal.com.core.domain.usecase.financialrecord.FinancialRecordPaginateUseCase
import acal.com.core.domain.valueobject.FinancialRecordFilter
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(
    value = ["/financial-record"],
)
class FinancialRecordController(
    private val paginate: FinancialRecordPaginateUseCase
) {

    @PostMapping("/paginate")
    @ResponseStatus(HttpStatus.OK)
    fun paginate(@RequestBody filter: FinancialRecordFilter): Page<FinancialRecordResponse> =
        paginate.execute(filter).toPage()

}
