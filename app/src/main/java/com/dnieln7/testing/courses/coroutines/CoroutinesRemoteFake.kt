package com.dnieln7.testing.courses.coroutines

class CoroutinesRemoteFake(private val data: String) : CoroutinesRemote {

    override suspend fun fetch(): String {
        return data
    }
}