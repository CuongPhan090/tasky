package com.cp.tasky.agenda.overview.data.model

data class OverviewSyncRequest (
    val deletedEventIds: List<String>,
    val deletedTaskIds: List<String>,
    val deletedReminderIds: List<String>
)
