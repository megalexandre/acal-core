package acal.com.core.comons

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*


fun BigDecimal.currency(): String  =
    NumberFormat
        .getCurrencyInstance(Locale.of("pt", "BR"))
        .format(this)