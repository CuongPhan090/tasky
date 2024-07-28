package com.cp.tasky.core.data.util

sealed interface DataError: Error{
    enum class Remote: DataError {
        SERVER_NOT_AVAILABLE,
        UNAUTHORIZED,
        EMAIL_ALREADY_EXIST,
        UNKNOWN,
        UNABLE_TO_SYNC
    }
}
