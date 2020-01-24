package cafe.adriel.dalek

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertArrayEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class DalekTest {

    @Test
    fun `should emit success event`() = runBlockingTest {
        val message = "No power in the universe can stop the Daleks!"

        val expectedEvents = arrayOf(Start, Success(message), Finish)

        val emittedEvents = Dalek(TestCoroutineDispatcher()) {
            message
        }.toArray()

        assertArrayEquals(expectedEvents, emittedEvents)
    }

    @Test
    fun `should emit failure event`() = runBlockingTest {
        val exception = RuntimeException("Exterminate! Exterminate! Exterminate!")

        val expectedEvents = arrayOf(Start, Failure(exception), Finish)

        val emittedEvents = Dalek(TestCoroutineDispatcher()) {
            throw exception
        }.toArray()

        assertArrayEquals(expectedEvents, emittedEvents)
    }
}
