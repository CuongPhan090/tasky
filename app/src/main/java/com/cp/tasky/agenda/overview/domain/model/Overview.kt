package com.cp.tasky.agenda.overview.domain.model

import com.cp.tasky.agenda.shared.domain.model.Event
import com.cp.tasky.agenda.shared.domain.model.Reminder
import com.cp.tasky.agenda.shared.domain.model.Task

data class Overview(
    val events: List<Event>,
    val tasks: List<Task>,
    val reminders: List<Reminder>
)
