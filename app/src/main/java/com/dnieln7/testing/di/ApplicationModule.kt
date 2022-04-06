package com.dnieln7.testing.di

import android.content.Context
import androidx.room.Room
import com.dnieln7.testing.BuildConfig
import com.dnieln7.testing.network.spacex.SpacexApi
import com.dnieln7.testing.persistance.AppDatabase
import com.dnieln7.testing.persistance.spacex.dao.MissionDao
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideSpacexApi(moshi: Moshi): SpacexApi {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.SPACEX_API)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(SpacexApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    fun provideMissionDao(appDatabase: AppDatabase): MissionDao {
        return appDatabase.missionDao()
    }
}