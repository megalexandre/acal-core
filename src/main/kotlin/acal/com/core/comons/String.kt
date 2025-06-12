package acal.com.core.comons

fun String.numbersOnly(): String = this.replace("[^0-9]".toRegex(), "")

fun String.asCPF(): String = this.replaceFirst("(\\d{3})(\\d{3})(\\d{3})(\\d{2})".toRegex(), "$1.$2.$3-$4")
fun String.asCNPJ(): String = this.replaceFirst("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})".toRegex(), "$1.$2.$3/$4-$5")

fun String.asPhoneNumber(): String = when (this.length) {
    10 -> this.replaceFirst("(\\d{2})(\\d{4})(\\d{4})".toRegex(), "($1) $2-$3")
    11 -> this.replaceFirst("(\\d{2})(\\d{5})(\\d{4})".toRegex(), "($1) $2-$3")
    else -> this
}

