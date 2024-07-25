package com.cp.tasky

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.cp.tasky.auth.shared.navigation.setUpAuthGraph
import com.cp.tasky.agenda.navigation.AgendaScreen
import com.cp.tasky.agenda.navigation.setUpPlannerGraph
import com.cp.tasky.ui.theme.TaskyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            // TODO: Check if the user has an active session, navigate to the agenda screen,
            //  else to login scree
        }

        setContent {
            TaskyTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = AgendaScreen.Overview) {
                    setUpAuthGraph(navController = navController)
                    setUpPlannerGraph(navController = navController)
                }
            }
        }
    }
}