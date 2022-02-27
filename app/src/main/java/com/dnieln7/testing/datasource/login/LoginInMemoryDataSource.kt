package com.dnieln7.testing.datasource.login

import com.dnieln7.testing.model.LoginRequest
import com.dnieln7.testing.model.LoginResponse
import kotlinx.coroutines.delay

class LoginInMemoryDataSource : LoginRemoteDataSource {

    private val users = mapOf(
        "dnieln7@gmail.com" to "asdfghjk",
        "admin@gmail.com" to "admin@password"
    )

    override suspend fun login(loginRequest: LoginRequest): LoginResponse {
        delay(3000)

        val result = users[loginRequest.email]

        return when {
            result == null -> LoginResponse(
                success = false,
                error = "User does not exists"
            )
            result != loginRequest.password -> LoginResponse(
                success = false,
                error = "Wrong password"
            )
            result == loginRequest.password -> LoginResponse(
                success = true,
                token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9"
            )
            else -> LoginResponse(
                success = false,
                error = "Internal server error"
            )
        }
    }
}