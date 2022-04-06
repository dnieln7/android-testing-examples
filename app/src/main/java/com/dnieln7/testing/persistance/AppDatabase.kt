package com.dnieln7.testing.persistance

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dnieln7.testing.model.cats.Cat
import com.dnieln7.testing.model.spacex.Mission
import com.dnieln7.testing.persistance.cats.dao.CatDao
import com.dnieln7.testing.persistance.spacex.dao.MissionDao

@Database(
    entities = [Cat::class, Mission::class],
    version = 1,
    exportSchema = true,
    autoMigrations = []
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun catDao(): CatDao

    abstract fun missionDao(): MissionDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()

                INSTANCE = instance

                return instance
            }
        }
    }
}