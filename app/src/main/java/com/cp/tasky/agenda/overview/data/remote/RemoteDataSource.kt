package com.cp.tasky.agenda.overview.data.remote

import com.cp.tasky.agenda.overview.data.model.OverviewApiResponse
import com.cp.tasky.agenda.overview.data.model.OverviewSyncRequest
import com.cp.tasky.core.data.util.DataError
import com.cp.tasky.core.data.util.Error
import com.cp.tasky.core.data.util.Result
import retrofit2.Response
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

interface RemoteDataResource {
    suspend fun getAgendaByDate(timeStamp: Long): Result<OverviewApiResponse, Error>

    suspend fun syncAgenda(overviewSyncRequest: OverviewSyncRequest): Result<Unit, Error>

    suspend fun getFullAgenda(): Result<OverviewApiResponse, Error>
}

class RemoteDataSourceImpl @Inject constructor(
    private val overviewApi: OverviewApi,
) : RemoteDataResource {
    override suspend fun getAgendaByDate(timeStamp: Long): Result<OverviewApiResponse, Error> {
        return handleApiResponse(
            apiCall = { overviewApi.getAgendaByDate(timeStamp) },
            errorHandler = { code ->
                // TODO: Specific error code will be added once integrating logging
                if (code in 500..599) DataError.Remote.SERVER_NOT_AVAILABLE
                else DataError.Remote.UNKNOWN
            }
        )
    }

    override suspend fun syncAgenda(overviewSyncRequest: OverviewSyncRequest): Result<Unit, Error> {
        return handleApiResponse(
            apiCall = { overviewApi.syncAgenda(overviewSyncRequest) },
            errorHandler = { DataError.Remote.UNABLE_TO_SYNC }
        )
    }

    override suspend fun getFullAgenda(): Result<OverviewApiResponse, Error> {
        return handleApiResponse(
            apiCall = { overviewApi.getFullAgenda() },
            errorHandler = { code ->
                // TODO: Specific error code will be added once integrating logging
                if (code in 500..599) DataError.Remote.SERVER_NOT_AVAILABLE
                else DataError.Remote.UNKNOWN
            }
        )
    }

    private suspend fun <T> handleApiResponse(
        apiCall: suspend () -> Response<T>,
        successHandler: (T) -> Result<T, DataError> = { Result.Success(it) },
        errorHandler: (Int) -> DataError = { DataError.Remote.UNKNOWN },
    ): Result<T, DataError> {
        return try {
            val response = apiCall()

            if (response.isSuccessful) {
                response.body()?.let {
                    successHandler(it)
                } ?: Result.Error(DataError.Remote.UNKNOWN)
            } else {
                Result.Error(errorHandler(response.code()))
            }
        } catch (e: Exception) {
            // Stop coroutine if it get cancelled, or time out
            if (e is CancellationException) throw e
            Result.Error(DataError.Remote.UNKNOWN)
        }
    }
}
