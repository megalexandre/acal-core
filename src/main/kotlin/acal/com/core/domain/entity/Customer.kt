package acal.com.core.domain.entity

import acal.com.core.comons.asCNPJ
import acal.com.core.comons.asCPF
import acal.com.core.comons.numbersOnly
import acal.com.core.domain.enums.DocumentType
import acal.com.core.domain.enums.DocumentType.CNPJ
import acal.com.core.domain.enums.DocumentType.CPF


data class Customer (
     val id: String,
     val auditInfo: AuditInfo,
     val name: String,
     val document: Document,
     val phoneNumber: String? = null,
     val partnerNumber: String? = null,
     val isAVoter: Boolean
)

class Document(value: String){
    private val value: String = value.numbersOnly()
    private val type: DocumentType
        get() = when (value.length){
            11 -> CPF
            14 -> CNPJ
            else -> throw IllegalArgumentException("Invalid document length: ${value.length}. Expected 11 for CPF or 14 for CNPJ.")
        }

    val number: String
        get() = when (type){
            CPF -> value.asCPF()
            CNPJ -> value.asCNPJ()
        }

}

