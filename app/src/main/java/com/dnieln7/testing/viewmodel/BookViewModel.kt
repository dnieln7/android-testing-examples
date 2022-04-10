package com.dnieln7.testing.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dnieln7.testing.model.book.Book
import com.dnieln7.testing.repository.book.IBookRepository
import com.dnieln7.testing.utils.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    private val iBookRepository: IBookRepository
) : ViewModel() {

    private val _apiResponse = MutableLiveData<ApiResponse>()
    val apiResponse: LiveData<ApiResponse> = _apiResponse

    val books get() = iBookRepository.observe()

    init {
        get()
    }

    fun get() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) { iBookRepository.get() }

            _apiResponse.value = result
        }
    }

    fun delete(book: Book) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) { iBookRepository.delete(book) }
        }
    }
}