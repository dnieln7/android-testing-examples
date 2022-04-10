package com.dnieln7.testing.di

import com.dnieln7.testing.datasource.spacex.mission.MissionLocalDataSource
import com.dnieln7.testing.datasource.spacex.mission.MissionRemoteDataSource
import com.dnieln7.testing.network.books.BooksApi
import com.dnieln7.testing.persistance.books.dao.BookDao
import com.dnieln7.testing.repository.book.BookRepository
import com.dnieln7.testing.repository.book.IBookRepository
import com.dnieln7.testing.repository.spacex.MissionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class ViewModelModule {

    @Provides
    @ViewModelScoped
    fun provideMissionRepository(
        remoteDataSource: MissionRemoteDataSource,
        localDataSource: MissionLocalDataSource
    ): MissionRepository {
        return MissionRepository(remoteDataSource, localDataSource)
    }

    @Provides
    @ViewModelScoped
    fun provideIBookRepository(booksApi: BooksApi, bookDao: BookDao): IBookRepository {
        return BookRepository(booksApi, bookDao)
    }
}