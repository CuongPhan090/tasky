package com.cp.tasky

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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