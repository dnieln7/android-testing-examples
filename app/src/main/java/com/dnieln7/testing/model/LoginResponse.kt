package com.dnieln7.testing.model

data class LoginResponse(val success: Boolean, val error: String? = null, val token: String? = null)
