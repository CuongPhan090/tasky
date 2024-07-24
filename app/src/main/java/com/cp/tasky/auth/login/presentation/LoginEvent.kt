package com.cp.tasky.auth.login.presentation

sealed interface LoginEvent{
    data object LoginSuccess: LoginEvent
}