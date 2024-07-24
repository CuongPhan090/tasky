package com.cp.tasky.auth.shared.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cp.tasky.auth.login.presentation.LoginScreenRoot
import com.cp.tasky.auth.register.presentation.RegisterScreenRoot
import com.cp.tasky.plannerhub.navigation.PlannerScreen

@Composable
fun SetUpAuthGraph(
    navHostController: NavHostController = rememberNavController(),
    startDestination: String = AuthScreen.Login.route,
) {

    // TODO: Upgrade to type-safe navigation
    NavHost(
        navController = navHostController,
        startDestination = startDestination
    ) {
        composable(route = AuthScreen.Login.route) {
            LoginScreenRoot(
                onLoginSuccess = {
                    navHostController.navigate(route = PlannerScreen.Agenda.route)
                    // TODO: Implement nested graph and pop the authGraph
                },
                onRegisterScreenClick =  {
                    navHostController.navigate(route = AuthScreen.Register.route)
                }
            )
        }

        composable(route = AuthScreen.Register.route) {
            RegisterScreenRoot(
                onBackButtonClick = {
                    navHostController.navigateUp()
                },
                onRegisterSuccess = {
                    navHostController.navigateUp()
                }
            )
        }
    }
}
