package acal.com.core.domain.entity

import java.time.Year

class LinkNumber(
    val year: Year,
    val sequence: String
) {
    val number get() = "${year.value}-${sequence}"
}