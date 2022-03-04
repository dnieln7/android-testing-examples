package com.dnieln7.testing.di

import com.dnieln7.testing.datasource.login.LoginInMemoryDataSource
import com.dnieln7.testing.network.Api
import com.dnieln7.testing.repository.LoginRepository

class ServiceLocator {
    private val loginRemoteDataSource by lazy { LoginInMemoryDataSource() }

    val loginRepository by lazy { LoginRepository(loginRemoteDataSource) }

    val api by lazy { Api("https://android-kotlin-fun-mars-server.appspot.com/") }
}