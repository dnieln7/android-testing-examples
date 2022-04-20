package com.dnieln7.testing.di

import android.content.Context
import androidx.room.Room
import com.dnieln7.testing.persistance.AppDatabase
import com.dnieln7.testing.repository.book.FakeBookRepository
import com.dnieln7.testing.repository.book.IBookRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object TestModule {

    @Named("test_db")
    @Provides
    fun provideTestAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }
}