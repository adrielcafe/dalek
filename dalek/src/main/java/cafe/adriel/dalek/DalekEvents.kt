package cafe.adriel.dalek

sealed class DalekEvent<out T>

object Start : DalekEvent<Nothing>()

data class Success<out T>(val value: T) : DalekEvent<T>()

data class Failure(val exception: Throwable) : DalekEvent<Nothing>()

object Finish : DalekEvent<Nothing>()
