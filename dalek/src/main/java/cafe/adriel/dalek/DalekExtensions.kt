package cafe.adriel.dalek

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

fun <T> Flow<T>.launchAndCollect(
    scope: CoroutineScope,
    action: suspend (value: T) -> Unit
): Job =
    scope.launch {
        collect(action)
    }
