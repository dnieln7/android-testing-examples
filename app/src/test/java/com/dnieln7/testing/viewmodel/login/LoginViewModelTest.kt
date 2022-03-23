package com.dnieln7.testing.viewmodel.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dnieln7.testing.datasource.login.LoginInMemoryDataSource
import com.dnieln7.testing.repository.login.LoginRepository
import com.dnieln7.testing.viewmodel.LoginViewModel
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class LoginViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `loading and success on false and error null on resetState()`() {
        val remoteDataSource = LoginInMemoryDataSource()
        val repository = LoginRepository(remoteDataSource)
        val viewModel = LoginViewModel(repository)
        viewModel.loginState.observeForever { }
        viewModel.resetState()

        assertEquals(false, viewModel.loginState.value?.loading)
        assertEquals(false, viewModel.loginState.value?.success)
        assertEquals(null, viewModel.loginState.value?.error)
    }
}