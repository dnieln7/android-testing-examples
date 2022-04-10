package com.dnieln7.testing.datasource.spacex.mission

import androidx.lifecycle.LiveData
import com.dnieln7.testing.model.spacex.Mission
import com.dnieln7.testing.persistance.spacex.dao.MissionDao
import javax.inject.Inject

class MissionRoomDataSource @Inject constructor(
    private val dao: MissionDao
) : MissionLocalDataSource {

    override fun getMissions(): LiveData<List<Mission>> {
        return dao.observe()
    }

    override suspend fun saveMissions(missions: List<Mission>) {
        dao.saveAll(missions)
    }
}