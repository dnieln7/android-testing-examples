package com.dnieln7.testing.di

import com.dnieln7.testing.datasource.spacex.mission.MissionApiDataSource
import com.dnieln7.testing.datasource.spacex.mission.MissionLocalDataSource
import com.dnieln7.testing.datasource.spacex.mission.MissionRemoteDataSource
import com.dnieln7.testing.datasource.spacex.mission.MissionRoomDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class MissionModule {

    @Binds
    @ViewModelScoped
    abstract fun provideMissionRemoteDataSource(missionApiDataSource: MissionApiDataSource): MissionRemoteDataSource

    @Binds
    @ViewModelScoped
    abstract fun provideMissionLocalDataSource(missionRoomDataSource: MissionRoomDataSource): MissionLocalDataSource
}