package com.cp.tasky.auth.shared.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.cp.tasky.auth.login.presentation.LoginScreenRoot
import com.cp.tasky.auth.register.presentation.RegisterScreenRoot
import com.cp.tasky.agenda.shared.navigation.AgendaScreen

fun NavGraphBuilder.setUpAuthGraph(
    navController: NavHostController,
) {

    navigation<AuthScreen.Route>(startDestination = AuthScreen.Login) {
        composable<AuthScreen.Login> {
            LoginScreenRoot(
                onLoginSuccess = {
                    navController.navigate(route = AgendaScreen.Overview) {
                        popUpTo(AuthScreen.Route) {
                            inclusive = true
                        }
                    }
                },
                onRegisterScreenClick =  {
                    navController.navigate(route = AuthScreen.Register)
                }
            )
        }

        composable<AuthScreen.Register> {
            RegisterScreenRoot(
                onBackButtonClick = {
                    navController.navigateUp()
                },
                onRegisterSuccess = {
                    navController.navigateUp()
                }
            )
        }
    }
}
