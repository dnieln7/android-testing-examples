package com.dnieln7.testing.network.spacex

import com.dnieln7.testing.model.spacex.Mission
import retrofit2.Response
import retrofit2.http.GET

interface SpacexApi {

    @GET("missions")
    suspend fun getMissions(): Response<List<Mission>>
}