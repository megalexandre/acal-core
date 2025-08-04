package acal.com.core.domain.entity

class InvoiceNumber(
    val reference: Reference,
    val number: Long
){

    val value: String
        get() = "${reference}/${number.toString().padStart(6, '0')}"

    val next: InvoiceNumber
        get() = InvoiceNumber(reference, number+1)
}