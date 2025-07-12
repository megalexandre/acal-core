package acal.com.core.domain.entity

import java.time.Month
import java.time.Year

data class Reference(
    val year: Year,
    val month: Month,
) {

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
            if (parts.size != 2) throw IllegalArgumentException("Invalid reference format. Expected 'YYYY-MM'.")
            return Year.of(parts[0].toInt())
        }

        private fun parseMonth(value: String): Month {
            val parts = value.split("-")
            if (parts.size != 2) throw IllegalArgumentException("Invalid reference format. Expected 'YYYY-MM'.")
            return Month.of(parts[1].toInt())
        }
    }

    override fun toString(): String = "${year.value}-${month.value.toString().padStart(2, '0')}"
}