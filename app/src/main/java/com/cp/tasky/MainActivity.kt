package com.cp.tasky

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.cp.tasky.agenda.shared.navigation.AgendaScreen
import com.cp.tasky.agenda.shared.navigation.setUpAgendaGraph
import com.cp.tasky.auth.shared.navigation.AuthScreen
import com.cp.tasky.auth.shared.navigation.setUpAuthGraph
import com.cp.tasky.auth.shared.presentation.AuthViewModel
import com.cp.tasky.ui.theme.TaskyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val authViewModel: AuthViewModel by viewModels()

        installSplashScreen().apply {
            authViewModel.isAuthenticated()
        }

        setContent {
            TaskyTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = if (authViewModel.isAuthenticatedUser) AgendaScreen.Overview else AuthScreen.Route
                ) {
                    setUpAuthGraph(navController = navController)
                    setUpAgendaGraph(navController = navController)
                }
            }
        }
    }
}