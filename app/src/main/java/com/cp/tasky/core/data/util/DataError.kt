package com.cp.tasky.core.data.util

sealed interface DataError: Error{
    enum class Remote: DataError {
        SERVER_NOT_AVAILABLE,
        UNAUTHORIZED,
        UNKNOWN
    }
}
