package gowebs.`in`.gopay_sdk

import android.content.Context
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mockito.mock
import java.io.IOException

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    // Create a mock context
    private val context: Context = mock(Context::class.java)
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun subtraction_isCorrect() {
        assertEquals(2, 4 - 2)
    }

    @Test
    fun multiplication_isCorrect() {
        assertEquals(6, 2 * 3)
    }

    @Test
    fun division_isCorrect() {
        assertEquals(3, 6 / 2)
    }

    @Test
    fun division_byZero_shouldThrowException() {
        try {
            val result = 6 / 0
            fail("Expected an ArithmeticException to be thrown")
        } catch (e: ArithmeticException) {
            assertEquals("/ by zero", e.message)
        }
    }

    @Test
    fun stringConcatenation_isCorrect() {
        val str1 = "Hello, "
        val str2 = "World!"
        assertEquals("Hello, World!", str1 + str2)
    }
}