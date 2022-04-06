package com.dnieln7.testing.datasource.spacex.mission

import com.dnieln7.testing.model.spacex.Mission
import com.dnieln7.testing.network.spacex.SpacexApi
import retrofit2.Response
import javax.inject.Inject

class MissionApiDataSource @Inject constructor(
    private val api: SpacexApi
) : MissionRemoteDataSource {

    override suspend fun getMissions(): Response<List<Mission>> {
        return api.getMissions()
    }
}