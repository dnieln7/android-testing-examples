package com.dnieln7.testing.datasource.login

import com.dnieln7.testing.model.LoginRequest
import com.dnieln7.testing.model.LoginResponse

interface LoginRemoteDataSource {
    suspend fun login(loginRequest: LoginRequest): LoginResponse
}