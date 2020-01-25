package cafe.adriel.dalek

import io.kotlintest.matchers.collections.shouldContainExactly
import io.kotlintest.specs.StringSpec
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.TestCoroutineDispatcher

class DalekTest : StringSpec({

    suspend fun <T> evaluate(events: List<DalekEvent<T>>, action: suspend () -> T) {
        val result = Dalek(TestCoroutineDispatcher(), action).toList()

        result shouldContainExactly events
    }

    "should emit success event when value is an object" {
        val value = "No power in the universe can stop the Daleks!"
        val events = listOf(Start, Success(value), Finish)

        evaluate(events) { value }
    }

    "should emit success event when value is null" {
        val value = null
        val events = listOf(Start, Success(value), Finish)

        evaluate(events) { value }
    }

    "should emit success event when value is Unit" {
        val value = Unit
        val events = listOf(Start, Success(value), Finish)

        evaluate(events) { value }
    }

    "should emit failure event when throw exception" {
        val exception = RuntimeException("Exterminate! Exterminate! Exterminate!")
        val events = listOf(Start, Failure(exception), Finish)

        evaluate(events) { throw exception }
    }
})
