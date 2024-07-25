package com.cp.tasky.agenda.navigation

import kotlinx.serialization.Serializable

sealed class AgendaScreen {
    @Serializable
    data object Route : AgendaScreen()

    @Serializable
    data object Overview : AgendaScreen()

    @Serializable
    data object Items : AgendaScreen()
}
