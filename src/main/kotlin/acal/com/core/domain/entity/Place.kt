package acal.com.core.domain.entity

data class Place (
    val id: String,
    val name: String? = null,
    val number: String,
    val letter: String? = null,
    val address: Address,
){
    val fullName: String
        get() = listOfNotNull(
            name?.let { "$it," },
            "${address.name} $number", letter).joinToString(" ")
}