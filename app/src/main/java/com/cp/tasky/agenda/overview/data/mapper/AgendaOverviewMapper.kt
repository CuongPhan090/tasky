package com.cp.tasky.agenda.overview.data.mapper

import com.cp.tasky.agenda.overview.data.model.OverviewApiResponse
import com.cp.tasky.agenda.overview.domain.model.Overview

fun OverviewApiResponse.toOverView(): Overview {
    return Overview(
        events = events,
        tasks = tasks,
        reminders = reminders
    )
}
