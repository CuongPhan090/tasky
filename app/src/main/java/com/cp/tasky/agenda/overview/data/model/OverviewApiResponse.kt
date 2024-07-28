package com.cp.tasky.agenda.overview.data.model

import com.cp.tasky.agenda.shared.data.model.EventDto
import com.cp.tasky.agenda.shared.data.model.ReminderDto
import com.cp.tasky.agenda.shared.data.model.TaskDto
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OverviewApiResponse(
    val events: List<EventDto>,
    val tasks: List<TaskDto>,
    val reminders: List<ReminderDto>
)
