package com.cp.tasky.agenda.overview.domain

import com.cp.tasky.agenda.overview.domain.model.Overview
import com.cp.tasky.core.data.util.Error
import com.cp.tasky.core.data.util.Result

interface OverviewRepository {
    suspend fun getAgendaByDate(timeStamp: Long): Result<Overview, Error>

    suspend fun syncAgendaRemotely(): Result<Unit, Error>

    suspend fun getFullAgenda(): Result<Overview, Error>
}
