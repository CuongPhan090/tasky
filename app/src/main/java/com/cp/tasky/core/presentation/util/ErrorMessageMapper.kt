package com.cp.tasky.core.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.cp.tasky.R
import com.cp.tasky.core.data.util.DataError

@Composable
fun DataError.getErrorMessage(): String {
    return when (this) {
        DataError.Remote.SERVER_NOT_AVAILABLE -> stringResource(R.string.server_not_available)
        DataError.Remote.UNAUTHORIZED -> stringResource(R.string.invalid_email_or_password)
        DataError.Remote.UNKNOWN -> stringResource(R.string.unknown_error)
        DataError.Remote.EMAIL_ALREADY_EXIST -> stringResource(id = R.string.email_already_exist)
        DataError.Remote.UNABLE_TO_SYNC -> stringResource(R.string.unable_to_sync)
    }
}
