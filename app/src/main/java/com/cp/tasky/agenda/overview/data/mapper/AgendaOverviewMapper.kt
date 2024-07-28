package com.cp.tasky.agenda.overview.data.mapper

import com.cp.tasky.agenda.overview.data.model.OverviewApiResponse
import com.cp.tasky.agenda.overview.domain.model.Overview
import com.cp.tasky.agenda.shared.domain.model.Attendee
import com.cp.tasky.agenda.shared.domain.model.Event
import com.cp.tasky.agenda.shared.domain.model.Photo
import com.cp.tasky.agenda.shared.domain.model.Reminder
import com.cp.tasky.agenda.shared.domain.model.Task

fun OverviewApiResponse.toOverView(): Overview {
    return Overview(
        events = events.map {
            Event(
                id = it.id,
                title = it.title,
                description = it.description,
                from = it.from,
                to = it.to,
                remindAt = it.remindAt,
                isUserEventCreator = it.isUserEventCreator,
                host = it.host,
                attendees = it.attendees.map { attendee ->
                    Attendee(
                        email = attendee.email,
                        fullName = attendee.fullName,
                        userId = attendee.userId,
                        isGoing = attendee.isGoing,
                        remindAt = attendee.remindAt,
                        eventId = attendee.eventId
                    )
                },
                photos = it.photos.map { photo ->
                    Photo(
                        key = photo.key,
                        url = photo.url
                    )
                }
            )
        },
        tasks = tasks.map {
            Task(
                id = it.id,
                title = it.title,
                description = it.description,
                time = it.time,
                remindAt = it.remindAt,
                isDone = it.isDone
            )
        },
        reminders = reminders.map {
            Reminder(
                id = it.id,
                title = it.title,
                description = it.description,
                time = it.time,
                remindAt = it.remindAt
            )
        }
    )
}
