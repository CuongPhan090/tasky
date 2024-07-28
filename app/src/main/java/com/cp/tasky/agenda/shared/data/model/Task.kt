package com.cp.tasky.agenda.shared.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Task(
    val id: String,
    val title: String,
    val description: String?,
    val time: Long,
    override val remindAt: Long,
    val isDone: Boolean,
) : AgendaItem
