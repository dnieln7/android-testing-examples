package com.dnieln7.testing.courses.coroutines

class CoroutinesRepository(
    private val remote: CoroutinesRemote,
    private val local: CoroutinesLocal
) {

    suspend fun sync() {
        try {
            val text = remote.fetch()
            val number = text.toInt()

            local.insert(number)
        } catch (cause: Throwable) {
            throw Exception("Error syncing", cause)
        }
    }
}