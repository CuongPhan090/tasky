package com.cp.tasky.plannerhub.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

fun NavGraphBuilder.setUpPlannerGraph(
    navController: NavHostController
) {

    composable<PlannerScreen.Agenda> {
        // TODO: Add Agenda Screen
    }

    composable<PlannerScreen.TaskDetail> {
        // TODO: Add TaskDetail Screen
    }

    composable<PlannerScreen.EventDetail> {
        // TODO: Add EventDetail Screen
    }

    composable<PlannerScreen.ReminderDetail> {
        // TODO: Add ReminderDetail Screen
    }
}
