package com.cp.tasky.agenda.shared.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EventDto(
    val id: String,
    val title: String,
    val description: String,
    val from: Long,
    val to: Long,
    override val remindAt: Long,
    val host: String,
    val isUserEventCreator: Boolean,
    val attendees: List<AttendeeDto>,
    val photos: List<PhotoDto>,
) : AgendaItemDto

@JsonClass(generateAdapter = true)
data class AttendeeDto(
    val email: String,
    val fullName: String,
    val userId: String,
    val eventId: String,
    val isGoing: Boolean,
    val remindAt: Long,
)

@JsonClass(generateAdapter = true)
data class PhotoDto(
    val key: String,
    val url: String,
)
