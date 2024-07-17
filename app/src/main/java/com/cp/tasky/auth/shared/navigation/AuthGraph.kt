package com.cp.tasky.auth.shared.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cp.tasky.auth.login.presentation.LoginScreenRoot
import com.cp.tasky.auth.register.presentation.RegisterScreenRoot

@Composable
fun SetUpAuthGraph(
    navHostController: NavHostController = rememberNavController(),
    startDestination: String = AuthScreen.Login.route,
) {

    NavHost(
        navController = navHostController,
        startDestination = startDestination
    ) {
        composable(route = AuthScreen.Login.route) {
            LoginScreenRoot()
        }

        composable(route = AuthScreen.Register.route) {
            RegisterScreenRoot()
        }
    }
}
