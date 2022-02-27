package com.dnieln7.testing.repository

import com.dnieln7.testing.datasource.login.LoginRemoteDataSource
import com.dnieln7.testing.model.LoginRequest
import com.dnieln7.testing.model.LoginResponse

class LoginRepository(private val dataSource: LoginRemoteDataSource) {

    suspend fun login(loginRequest: LoginRequest): LoginResponse {
        return dataSource.login(loginRequest)
    }
}