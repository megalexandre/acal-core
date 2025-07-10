package acal.com.core.domain.entity

import acal.com.core.comons.asCNPJ
import acal.com.core.comons.asCPF
import acal.com.core.comons.asPhoneNumber
import acal.com.core.comons.numbersOnly
import acal.com.core.domain.enums.DocumentType
import acal.com.core.domain.enums.DocumentType.CNPJ
import acal.com.core.domain.enums.DocumentType.CPF

data class Customer (
    val id: String,
    val name: String,
    val identityCard: IdentityCard,
    val phoneNumber: PhoneNumber? = null,
    val partnerNumber: String? = null,
    val voter: Boolean
)

class IdentityCard(value: String){
    val raw: String = value.numbersOnly()
    private val type: DocumentType
        get() = when (raw.length){
            11 -> CPF
            else -> CNPJ
            ///else -> throw IllegalArgumentException("Invalid document length: ${raw.length}. Expected 11 for CPF or 14 for CNPJ.")
        }

    val number: String
        get() = when (type){
            CPF -> raw.asCPF()
            CNPJ -> raw.asCNPJ()
        }

}

class PhoneNumber(value: String) {

    val raw = value.numbersOnly()

    val number: String
        get() = raw.numbersOnly().asPhoneNumber()

}

