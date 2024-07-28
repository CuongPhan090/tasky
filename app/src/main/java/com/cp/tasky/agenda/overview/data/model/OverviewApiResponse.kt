package com.cp.tasky.agenda.overview.data.model

import com.cp.tasky.agenda.shared.data.model.Event
import com.cp.tasky.agenda.shared.data.model.Reminder
import com.cp.tasky.agenda.shared.data.model.Task
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OverviewApiResponse(
    val events: List<Event>,
    val tasks: List<Task>,
    val reminders: List<Reminder>
)
