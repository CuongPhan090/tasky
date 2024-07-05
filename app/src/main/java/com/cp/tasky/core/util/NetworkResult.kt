package com.cp.tasky.core.util

sealed class NetworkResult<out T> {
    data class Success<out T>(val data: T): NetworkResult<T>()
    data class Error<out T>(val exception: Throwable): NetworkResult<T>()
    data object Loading: NetworkResult<Nothing>()
    data object Idle: NetworkResult<Nothing>()
}
