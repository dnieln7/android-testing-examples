package com.dnieln7.testing.persistance.cats.dao

import androidx.room.*
import com.dnieln7.testing.model.cats.Cat
import kotlinx.coroutines.flow.Flow

@Dao
interface CatDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cat: Cat)

    @Update
    suspend fun update(cat: Cat)

    @Query("SELECT * FROM tb_cats ORDER BY name DESC")
    fun getCats(): Flow<List<Cat>>

    @Query("SELECT * from tb_cats WHERE id = :id")
    fun getCatById(id: Int): Flow<Cat>

    @Delete
    suspend fun delete(cat: Cat)
}