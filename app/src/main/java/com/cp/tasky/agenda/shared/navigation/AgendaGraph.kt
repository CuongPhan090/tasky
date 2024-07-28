package com.cp.tasky.agenda.shared.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

fun NavGraphBuilder.setUpAgendaGraph(
    navController: NavHostController
) {

    composable<AgendaScreen.Overview> {
        // TODO: Add Agenda Screen
    }

    composable<AgendaScreen.Items> {
        // TODO: Add TaskDetail Screen
    }
}
