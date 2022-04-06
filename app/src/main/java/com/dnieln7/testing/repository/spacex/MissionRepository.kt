package com.dnieln7.testing.repository.spacex

import androidx.lifecycle.LiveData
import com.dnieln7.testing.datasource.spacex.mission.MissionLocalDataSource
import com.dnieln7.testing.datasource.spacex.mission.MissionRemoteDataSource
import com.dnieln7.testing.model.spacex.Mission
import com.dnieln7.testing.utils.ApiResponse

class MissionRepository(
    private val missionRemoteDataSource: MissionRemoteDataSource,
    private val missionLocalDataSource: MissionLocalDataSource
) {

    suspend fun fetchMissions(): ApiResponse {
        val response = missionRemoteDataSource.getMissions()

        return if (response.isSuccessful && response.body() != null) {
            missionLocalDataSource.saveMissions(response.body()!!)

            ApiResponse.Success
        } else {
            ApiResponse.Error(response.code(), response.errorBody()?.toString() ?: "Unknown error")
        }
    }

    fun getMissions(): LiveData<List<Mission>> {
        return missionLocalDataSource.getMissions()
    }
}