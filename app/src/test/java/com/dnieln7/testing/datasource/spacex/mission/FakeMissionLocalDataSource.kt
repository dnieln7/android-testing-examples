package com.dnieln7.testing.datasource.spacex.mission

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dnieln7.testing.model.spacex.Mission

class FakeMissionLocalDataSource : MissionLocalDataSource {

    private val _missions = MutableLiveData<List<Mission>>()
    private val missionsL: LiveData<List<Mission>> = _missions

    override fun getMissions(): LiveData<List<Mission>> {
        return missionsL
    }

    override suspend fun saveMissions(missions: List<Mission>) {
        _missions.value = missions
    }
}