package com.dnieln7.testing.datasource.spacex.mission

import com.dnieln7.testing.model.spacex.Mission
import retrofit2.Response

interface MissionRemoteDataSource {

    suspend fun getMissions(): Response<List<Mission>>
}