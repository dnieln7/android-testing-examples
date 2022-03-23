package com.dnieln7.testing.repository.login

import com.dnieln7.testing.datasource.login.LoginRemoteDataSource
import com.dnieln7.testing.model.login.LoginRequest
import com.dnieln7.testing.model.login.LoginResponse

class LoginRepository(private val dataSource: LoginRemoteDataSource) {

    suspend fun login(loginRequest: LoginRequest): LoginResponse {
        return dataSource.login(loginRequest)
    }
}