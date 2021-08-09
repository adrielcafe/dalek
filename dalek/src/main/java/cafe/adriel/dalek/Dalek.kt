package cafe.adriel.dalek

import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

sealed class DalekEvent<out T> {
    object Start : DalekEvent<Nothing>()
    data class Success<out T>(val value: T) : DalekEvent<T>()
    data class Failure(val exception: Throwable) : DalekEvent<Nothing>()
    object Finish : DalekEvent<Nothing>()
}

fun <T> Dalek(
    context: CoroutineContext = Dispatchers.Default,
    action: suspend () -> T
): Flow<DalekEvent<T>> =
    flow<DalekEvent<T>> { emit(DalekEvent.Success(action())) }
        .catch { exception -> emit(DalekEvent.Failure(exception)) }
        .onStart { emit(DalekEvent.Start) }
        .onCompletion { emit(DalekEvent.Finish) }
        .flowOn(context)
