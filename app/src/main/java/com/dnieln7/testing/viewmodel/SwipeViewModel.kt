package com.dnieln7.testing.viewmodel

import androidx.lifecycle.*
import com.dnieln7.testing.model.book.Book
import com.dnieln7.testing.repository.book.IBookRepository
import com.dnieln7.testing.utils.DataResponse
import com.dnieln7.testing.work.drink.DrinkWorkManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SwipeViewModel constructor(private val iBookRepository: IBookRepository) : ViewModel() {

    private val _dataResponse = MutableLiveData<DataResponse<List<Book>>>()
    val dataResponse: LiveData<DataResponse<List<Book>>> = _dataResponse

    private val _deleteState = MutableLiveData<DeleteState>()
    val deleteState: LiveData<DeleteState> = _deleteState

    init {
        get()
    }

    fun get() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) { iBookRepository.get() }

            _dataResponse.value = result
        }
    }

    fun delete(adapterPos: Int, book: Book) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) { iBookRepository.delete(book) }

            _deleteState.value = DeleteState(success = true, deletedPos = adapterPos)
        }
    }

    data class DeleteState(
        val deletedPos: Int = -1,
        val success: Boolean = false,
        val error: String? = null
    )

    @Suppress("UNCHECKED_CAST")
    class Factory(private val iBookRepository: IBookRepository) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SwipeViewModel(iBookRepository) as T
        }
    }
}