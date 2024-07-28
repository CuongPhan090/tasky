package com.cp.tasky.agenda.shared.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Reminder(
    val id: String,
    val title: String,
    val description: String?,
    val time: Long,
    override val remindAt: Long,
) : AgendaItem
