package com.cp.tasky.agenda.overview.domain

import com.cp.tasky.agenda.overview.domain.model.Overview
import java.time.LocalDateTime

interface OverviewRepository {
    suspend fun getAgendaByDate(timeStamp: LocalDateTime): Overview

    suspend fun syncAgendaRemotely()

    suspend fun getFullAgenda(): Overview
}
