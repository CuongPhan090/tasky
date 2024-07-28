package com.cp.tasky.agenda.shared.domain.model

data class Reminder(
    val id: String,
    val title: String,
    val description: String?,
    val time: Long,
    override val remindAt: Long,
) : AgendaItem
