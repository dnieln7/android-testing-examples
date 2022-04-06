package com.dnieln7.testing.datasource.spacex.mission

import androidx.lifecycle.LiveData
import com.dnieln7.testing.model.spacex.Mission

interface MissionLocalDataSource {

    fun getMissions(): LiveData<List<Mission>>

    suspend fun saveMissions(missions: List<Mission>)
}