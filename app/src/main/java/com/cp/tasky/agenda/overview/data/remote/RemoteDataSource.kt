package com.cp.tasky.agenda.overview.data.remote

import com.cp.tasky.agenda.overview.data.model.OverviewApiResponse
import com.cp.tasky.agenda.overview.data.model.OverviewSyncRequest
import com.cp.tasky.core.data.util.Error
import com.cp.tasky.core.data.util.Result
import javax.inject.Inject

interface RemoteDataResource {
    suspend fun getAgendaByDate(timeStamp: Long): Result<OverviewApiResponse, Error>

    suspend fun syncAgenda(overviewSyncRequest: OverviewSyncRequest)

    suspend fun getFullAgenda(): Result<OverviewApiResponse, Error>
}

class RemoteDataSourceImpl @Inject constructor(
    private val overviewApi: OverviewApi
) : RemoteDataResource {
    override suspend fun getAgendaByDate(timeStamp: Long): Result<OverviewApiResponse, Error> {
        //TODO: Make API request and handle network error
    }

    override suspend fun syncAgenda(overviewSyncRequest: OverviewSyncRequest) {
        //TODO: Make API request and handle network error
    }

    override suspend fun getFullAgenda(): Result<OverviewApiResponse, Error> {
        //TODO: Make API request and handle network error
    }
}
