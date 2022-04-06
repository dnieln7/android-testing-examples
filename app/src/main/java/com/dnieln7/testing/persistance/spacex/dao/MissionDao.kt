package com.dnieln7.testing.persistance.spacex.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dnieln7.testing.model.spacex.Mission

@Dao
interface MissionDao {

    @Query("SELECT * FROM tb_missions")
    fun getMissions(): LiveData<List<Mission>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMissions(missions: List<Mission>)
}