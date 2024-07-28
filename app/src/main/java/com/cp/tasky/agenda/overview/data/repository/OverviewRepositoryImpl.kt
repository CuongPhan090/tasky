package com.cp.tasky.agenda.overview.data.repository

import com.cp.tasky.agenda.overview.data.remote.RemoteDataResource
import com.cp.tasky.agenda.overview.domain.OverviewRepository
import com.cp.tasky.agenda.overview.domain.model.Overview
import java.time.LocalDateTime
import javax.inject.Inject

class OverviewRepositoryImpl @Inject constructor(
    remoteDataResource: RemoteDataResource
): OverviewRepository {
    override suspend fun getAgendaByDate(timeStamp: LocalDateTime): Overview {
        TODO("Not yet implemented")
    }

    override suspend fun syncAgendaRemotely() {
        TODO("Not yet implemented")
    }

    override suspend fun getFullAgenda(): Overview {
        TODO("Not yet implemented")
    }
}
