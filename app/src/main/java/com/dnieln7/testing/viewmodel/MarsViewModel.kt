package com.dnieln7.testing.viewmodel

import androidx.lifecycle.*
import com.dnieln7.testing.model.mars.MarsPhoto
import com.dnieln7.testing.network.mars.MarsApi
import kotlinx.coroutines.launch

class MarsViewModel(private val marsApi: MarsApi) : ViewModel() {

    private val _photos = MutableLiveData<List<MarsPhoto>>()
    val photos: LiveData<List<MarsPhoto>> = _photos

    fun getPhotos() {
        viewModelScope.launch {
            _photos.value = marsApi.getPhotos()
        }
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(private val marsApi: MarsApi) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MarsViewModel(marsApi) as T
        }
    }
}