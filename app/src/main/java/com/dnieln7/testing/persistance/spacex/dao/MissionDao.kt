package com.dnieln7.testing.persistance.spacex.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dnieln7.testing.model.spacex.Mission

@Dao
interface MissionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(missions: List<Mission>)

    @Query("SELECT * FROM tb_missions")
    fun observe(): LiveData<List<Mission>>
}