package com.cp.tasky.auth.register.presentation

sealed interface RegisterEvent{
    data object RegisterSuccess: RegisterEvent
}