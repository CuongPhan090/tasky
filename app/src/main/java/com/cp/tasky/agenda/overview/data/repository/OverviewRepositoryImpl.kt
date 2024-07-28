package com.cp.tasky.agenda.overview.data.repository

import com.cp.tasky.agenda.overview.data.mapper.toOverView
import com.cp.tasky.agenda.overview.data.remote.RemoteDataResource
import com.cp.tasky.agenda.overview.domain.OverviewRepository
import com.cp.tasky.agenda.overview.domain.model.Overview
import com.cp.tasky.core.data.util.DataError
import com.cp.tasky.core.data.util.Error
import com.cp.tasky.core.data.util.Result
import com.cp.tasky.core.data.util.map
import javax.inject.Inject

class OverviewRepositoryImpl @Inject constructor(
    private val remoteDataResource: RemoteDataResource,
) : OverviewRepository {
    //TODO: Switching from `Long` to `LocalDataTime/ZonedDataTime` once impl the DatePickerDialog
    override suspend fun getAgendaByDate(timeStamp: Long): Result<Overview, Error> {
        // TODO: offline first
        return when (val agenda = remoteDataResource.getAgendaByDate(timeStamp)) {
            is Result.Success -> {
                agenda.map { it.toOverView() }
            }

            is Result.Error -> {
                // TODO: fall back to local database if available
                Result.Error(agenda.error)
            }
            else -> Result.Error(DataError.Remote.UNKNOWN)
        }
    }

    override suspend fun syncAgendaRemotely(): Result<Unit, Error> {
        // TODO: get overviewSyncRequest from ROOM to sync agenda items with remote database
        return Result.Loading // temp return value
    }

    override suspend fun getFullAgenda(): Result<Overview, Error> {
        return when (val agenda = remoteDataResource.getFullAgenda()) {
            is Result.Success -> {
                // TODO: sync device's local cache (ROOM) from remote database
                agenda.map { it.toOverView() }
            }

            is Result.Error -> {
                Result.Error(agenda.error)
            }
            else -> Result.Error(DataError.Remote.UNKNOWN)
        }
    }
}
