package com.dnieln7.testing.di

import android.content.Context
import com.dnieln7.testing.datasource.login.LoginInMemoryDataSource
import com.dnieln7.testing.network.mars.Api
import com.dnieln7.testing.persistance.AppDatabase
import com.dnieln7.testing.repository.login.LoginRepository

class ServiceLocator(context: Context) {
    private val loginRemoteDataSource by lazy { LoginInMemoryDataSource() }

    val loginRepository by lazy { LoginRepository(loginRemoteDataSource) }

    val api by lazy { Api("https://android-kotlin-fun-mars-server.appspot.com/") }
    val database by lazy { AppDatabase.getDatabase(context) }
}