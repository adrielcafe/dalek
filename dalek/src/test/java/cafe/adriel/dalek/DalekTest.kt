package cafe.adriel.dalek

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainExactly
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.TestCoroutineDispatcher

@ExperimentalCoroutinesApi
class DalekTest : StringSpec({

    val dispatcher = TestCoroutineDispatcher()

    suspend fun <T> evaluate(expectedEvents: List<DalekEvent<T>>, action: suspend () -> T) {
        val emittedEvents = Dalek(dispatcher, action).toList()

        emittedEvents shouldContainExactly expectedEvents
    }

    "should emit success event when value is an object" {
        val value = "No power in the universe can stop the Daleks!"
        val events = listOf(DalekEvent.Start, DalekEvent.Success(value), DalekEvent.Finish)

        evaluate(events) { value }
    }

    "should emit success event when value is null" {
        val value = null
        val events = listOf(DalekEvent.Start, DalekEvent.Success(value), DalekEvent.Finish)

        evaluate(events) { value }
    }

    "should emit success event when value is Any" {
        val value = "Obey!" as Any
        val events = listOf(DalekEvent.Start, DalekEvent.Success(value), DalekEvent.Finish)

        evaluate(events) { value }
    }

    "should emit success event when value is Unit" {
        val value = Unit
        val events = listOf(DalekEvent.Start, DalekEvent.Success(value), DalekEvent.Finish)

        evaluate(events) { value }
    }

    "should emit failure event when throw an exception" {
        val exception = IllegalStateException("Exterminate!")
        val events = listOf(DalekEvent.Start, DalekEvent.Failure(exception), DalekEvent.Finish)

        evaluate(events) { throw exception }
    }
})
