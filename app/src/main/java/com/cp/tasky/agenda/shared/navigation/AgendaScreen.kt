package com.cp.tasky.agenda.shared.navigation

import kotlinx.serialization.Serializable

sealed class AgendaScreen {
    @Serializable
    data object Overview : AgendaScreen()

    @Serializable
    data object Items : AgendaScreen()
}
