package com.cp.tasky.agenda.shared.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Event(
    val id: String,
    val title: String,
    val description: String,
    val from: Long,
    val to: Long,
    override val remindAt: Long,
    val host: String,
    val isUserEventCreator: Boolean,
    val attendees: List<Attendee>,
    val photos: List<Photo>,
) : AgendaItem

@JsonClass(generateAdapter = true)
data class Attendee(
    val email: String,
    val fullName: String,
    val userId: String,
    val eventId: String,
    val isGoing: Boolean,
    val remindAt: Long,
)

@JsonClass(generateAdapter = true)
data class Photo(
    val key: String,
    val url: String,
)
