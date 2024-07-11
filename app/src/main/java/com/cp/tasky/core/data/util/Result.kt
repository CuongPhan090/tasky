package com.cp.tasky.core.data.util

sealed interface Result<out D, out E : Error> {
    data class Success<out D>(val data: D) : Result<D, Nothing>
    data class Error<out E : com.cp.tasky.core.data.util.Error>(val error: E) : Result<Nothing, E>
    data object Loading : Result<Nothing, Nothing>
    data object Idle : Result<Nothing, Nothing>
}

inline fun <T, E : Error, R> Result<T, E>.map(map: (T) -> R): Result<R, E> {
    return when (this) {
        is Result.Error -> Result.Error(error)
        is Result.Success -> Result.Success(map(data))
        Result.Idle -> Result.Idle
        Result.Loading -> Result.Loading
    }
}

fun <T, E : Error> Result<T, E>.asEmptyDataResult(): EmptyResult<E> {
    return map { }
}

inline fun <T, E : Error> Result<T, E>.onSuccess(action: (T) -> Unit): Result<T, E> {
    return when (this) {
        is Result.Error -> this
        is Result.Success -> {
            action(data)
            this
        }

        Result.Idle -> Result.Idle
        Result.Loading -> Result.Loading
    }
}

inline fun <T, E : Error> Result<T, E>.onError(action: (E) -> Unit): Result<T, E> {
    return when (this) {
        is Result.Error -> {
            action(error)
            this
        }

        is Result.Success -> this

        Result.Idle -> Result.Idle
        Result.Loading -> Result.Loading
    }
}

typealias EmptyResult<E> = Result<Unit, E>
