package com.dnieln7.testing.utils

sealed class ApiResponse {
    object Success : ApiResponse()
    class Error(val code: Int, val message: String) : ApiResponse()
}
