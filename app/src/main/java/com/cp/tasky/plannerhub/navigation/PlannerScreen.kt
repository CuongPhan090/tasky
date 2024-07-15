package com.cp.tasky.plannerhub.navigation

sealed class PlannerScreen(val route: String) {
    data object Agenda: PlannerScreen("agenda")
    data object TaskDetail: PlannerScreen("task_detail") // prob need an id
    data object EventDetail: PlannerScreen("event_detail") // prob need an id
    data object ReminderDetail: PlannerScreen("reminder_detail") // prob need an id
}
