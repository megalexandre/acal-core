package acal.com.core.domain.entity

import java.time.Month
import java.time.Year

data class Reference(
    val year: Year,
    val month: Month,
) {

    fun lastMonth(): Reference {
        val lastMonth = month.minus(1)
        val lastYear = if (lastMonth == Month.DECEMBER) year.minusYears(1) else year
        return Reference(lastYear, lastMonth)
    }

    private constructor(value: String) : this(
        parseYear(value),
        parseMonth(value)
    )

    companion object {
        fun of(value: String): Reference{
            var data = value
            if(value.length == 6){
                data = value.substring(0, 4) + "-" + value.substring(4, 6)
            }

            return Reference(data)
        }

        private fun parseYear(value: String): Year {
            val parts = value.split("-")
            require(parts.size == 2) { "Invalid reference format. Expected 'YYYY-MM'." }
            return Year.of(parts[0].toInt())
        }

        private fun parseMonth(value: String): Month {
            val parts = value.split("-")
            require(parts.size == 2) { "Invalid reference format. Expected 'YYYY-MM'." }
            return Month.of(parts[1].toInt())
        }
    }

    override fun toString(): String = "${year.value}-${month.value.toString().padStart(2, '0')}"
}