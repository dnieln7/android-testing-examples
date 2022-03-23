package com.dnieln7.testing.viewmodel

import androidx.lifecycle.*
import com.dnieln7.testing.model.login.LoginRequest
import com.dnieln7.testing.repository.login.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(private val repository: LoginRepository) : ViewModel() {

    private val _loginState = MutableLiveData<LoginState>()
    val loginState: LiveData<LoginState> = _loginState

    fun login(email: String, password: String) {
        _loginState.value = LoginState(loading = true)

        viewModelScope.launch {
            val response = withContext(Dispatchers.IO) {
                repository.login(LoginRequest(email, password))
            }

            _loginState.value = LoginState(success = response.success, error = response.error)
        }
    }

    fun resetState() {
        _loginState.value = LoginState()
    }

    data class LoginState(
        val loading: Boolean = false,
        val success: Boolean = false,
        val error: String? = null
    )

    @Suppress("UNCHECKED_CAST")
    class Factory(private val repository: LoginRepository) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return LoginViewModel(repository) as T
        }
    }
}