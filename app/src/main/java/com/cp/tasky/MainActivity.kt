package com.cp.tasky

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.cp.tasky.auth.shared.navigation.SetUpAuthGraph
import com.cp.tasky.plannerhub.navigation.SetUpPlannerGraph
import com.cp.tasky.ui.theme.TaskyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskyTheme {
                SetUpAuthGraph()
                SetUpPlannerGraph()
            }
        }
    }
}