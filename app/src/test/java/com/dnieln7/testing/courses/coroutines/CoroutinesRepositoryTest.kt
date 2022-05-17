package com.dnieln7.testing.courses.coroutines

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.Test

@ExperimentalCoroutinesApi
class CoroutinesRepositoryTest {

    @Test
    fun sync() = runTest {
        val repository = CoroutinesRepository(
            CoroutinesRemoteFake("1"),
            CoroutinesLocalFake()
        )

        repository.sync()
    }
}