package com.cp.tasky.agenda.overview.data.remote

import com.cp.tasky.agenda.overview.data.model.OverviewApiResponse
import com.cp.tasky.agenda.overview.data.model.OverviewSyncRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface OverviewApi {
    @GET("/agenda")
    suspend fun getAgendaByDate(@Query("time") time: Long): Response<OverviewApiResponse>

    @POST("/syncAgenda")
    suspend fun syncAgenda(@Body agendaSyncRequest: OverviewSyncRequest)

    @GET("/fullAgenda")
    suspend fun getFullAgenda(): Response<OverviewApiResponse>
}
