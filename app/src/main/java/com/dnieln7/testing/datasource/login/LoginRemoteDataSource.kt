package com.dnieln7.testing.datasource.login

import com.dnieln7.testing.model.login.LoginRequest
import com.dnieln7.testing.model.login.LoginResponse

interface LoginRemoteDataSource {
    suspend fun login(loginRequest: LoginRequest): LoginResponse
}