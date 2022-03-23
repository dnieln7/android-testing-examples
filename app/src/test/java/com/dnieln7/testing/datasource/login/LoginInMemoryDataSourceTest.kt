package com.dnieln7.testing.datasource.login

import com.dnieln7.testing.model.login.LoginRequest
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test

class LoginInMemoryDataSourceTest {

    private lateinit var dataSource: LoginRemoteDataSource

    @Before
    fun setUp() {
        dataSource = LoginInMemoryDataSource()
    }

    @Test
    fun `user wrong email returns error`(): Unit = runBlocking {
        val result = dataSource.login(LoginRequest("asdmnoil", "password"))
        assertFalse(result.success)
    }

    @Test
    fun `user wrong password returns error`(): Unit = runBlocking {
        val result = dataSource.login(LoginRequest("admin@gmail.com", "admin"))
        assertFalse(result.success)
    }

    @Test
    fun `user correct credentials returns token`(): Unit = runBlocking {
        val result = dataSource.login(LoginRequest("admin@gmail.com", "admin@password"))
        assertTrue(result.token != null)
    }
}