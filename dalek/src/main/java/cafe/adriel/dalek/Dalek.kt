package cafe.adriel.dalek

import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

@ExperimentalCoroutinesApi
object Dalek {

    operator fun <T> invoke(
        context: CoroutineContext = Dispatchers.Default,
        action: suspend () -> T
    ): Flow<DalekEvent<T>> =
        flow<DalekEvent<T>> { emit(Success(action())) }
            .catch { exception -> emit(Failure(exception)) }
            .onStart { emit(Start) }
            .onCompletion { emit(Finish) }
            .flowOn(context)
}
