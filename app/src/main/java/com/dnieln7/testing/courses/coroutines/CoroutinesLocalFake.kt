package com.dnieln7.testing.courses.coroutines

class CoroutinesLocalFake : CoroutinesLocal {

    private val texts = mutableListOf<Int>()

    override suspend fun insert(number: Int) {
        texts.add(number)
    }
}