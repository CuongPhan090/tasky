package com.cp.tasky.plannerhub.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun SetUpPlannerGraph(
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController(),
    startDestination: String = PlannerScreen.Agenda.route
) {

    // TODO: Upgrade to type-safe navigation
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = startDestination
    ) {
        composable(route = PlannerScreen.Agenda.route) {
            // TODO: Add Agenda Screen
        }

        composable(route = PlannerScreen.TaskDetail.route) {
            // TODO: Add TaskDetail Screen
        }

        composable(route = PlannerScreen.EventDetail.route) {
            // TODO: Add EventDetail Screen
        }

        composable(route = PlannerScreen.ReminderDetail.route) {
            // TODO: Add ReminderDetail Screen
        }
    }
}
