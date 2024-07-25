package com.cp.tasky.agenda.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

fun NavGraphBuilder.setUpPlannerGraph(
    navController: NavHostController
) {

    composable<AgendaScreen.Overview> {
        // TODO: Add Agenda Screen
    }

    composable<AgendaScreen.Items> {
        // TODO: Add TaskDetail Screen
    }
}
