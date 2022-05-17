package com.dnieln7.testing.courses.coroutines

interface CoroutinesRemote {

    suspend fun fetch(): String
}