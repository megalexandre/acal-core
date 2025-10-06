package acal.com.core.domain.entity

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import mocks.place

class PlaceTest {

    @Test
    fun givenPlaceHasAllFields_whenFullName(){
        val fullName = place.fullName
        assertEquals(fullName, "Banheiros Publicos, Avenida Fernando Daltro 1 A")
    }

    @Test
    fun givenPlaceHasNoName_whenFullName(){
        val fullName = place.copy(name = null).fullName
        assertEquals(fullName, "Avenida Fernando Daltro 1 A")
    }

    @Test
    fun givenPlaceHasNoNameAndNoLetter_whenFullName(){
        val fullName = place.copy(name = null, letter = null).fullName
        assertEquals(fullName, "Avenida Fernando Daltro 1")
    }

    @Test
    fun givenPlaceHasNoLetter_whenFullName(){
        val fullName = place.copy(letter = null).fullName
        assertEquals(fullName, "Banheiros Publicos, Avenida Fernando Daltro 1")
    }


}