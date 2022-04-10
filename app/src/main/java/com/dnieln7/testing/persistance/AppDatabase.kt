package com.dnieln7.testing.persistance

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dnieln7.testing.model.book.Book
import com.dnieln7.testing.model.cats.Cat
import com.dnieln7.testing.model.spacex.Mission
import com.dnieln7.testing.persistance.books.converters.BookConverters
import com.dnieln7.testing.persistance.books.dao.BookDao
import com.dnieln7.testing.persistance.cats.dao.CatDao
import com.dnieln7.testing.persistance.spacex.dao.MissionDao

@Database(
    entities = [Cat::class, Mission::class, Book::class],
    version = 1,
    exportSchema = true,
    autoMigrations = []
)
@TypeConverters(BookConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun catDao(): CatDao

    abstract fun missionDao(): MissionDao

    abstract fun bookDao(): BookDao

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