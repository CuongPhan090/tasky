package com.cp.tasky.core.presentation.util

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.cp.tasky.R

sealed class UiText {
    data class DynamicString(val value: String): UiText()
    class StringResource(@StringRes val id: Int, vararg val args: Any): UiText()

    companion object {
        fun unknownError(): UiText {
            return UiText.StringResource(R.string.unknown_error)
        }
    }

    fun asString(context: Context): String {
        return when(this) {
            is DynamicString -> value
            is StringResource -> context.getString(id, *args)
        }
    }

    @Composable
    fun asString(): String {
        return when(this) {
            is DynamicString -> value
            is StringResource -> LocalContext.current.getString(id, *args)
        }
    }
}