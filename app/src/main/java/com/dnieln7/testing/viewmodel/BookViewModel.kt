package com.dnieln7.testing.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dnieln7.testing.model.book.Book
import com.dnieln7.testing.repository.book.IBookRepository
import com.dnieln7.testing.utils.DataResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    private val iBookRepository: IBookRepository
) : ViewModel() {

    private val _dataResponse = MutableLiveData<DataResponse<List<Book>>>()
    val dataResponse: LiveData<DataResponse<List<Book>>> = _dataResponse

    init {
        get()
    }

    fun get() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) { iBookRepository.get() }

            _dataResponse.value = result
        }
    }

    fun delete(book: Book) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) { iBookRepository.delete(book) }
        }
    }
}