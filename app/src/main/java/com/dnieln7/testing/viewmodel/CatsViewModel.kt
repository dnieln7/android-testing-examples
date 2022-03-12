package com.dnieln7.testing.viewmodel

import androidx.lifecycle.*
import com.dnieln7.testing.model.cats.Cat
import com.dnieln7.testing.persistance.cats.dao.CatDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CatsViewModel(private val catDao: CatDao) : ViewModel() {
    val cats: LiveData<List<Cat>> = catDao.getCats().asLiveData()

    fun getById(id: Int): LiveData<Cat> {
        return catDao.getCatById(id).asLiveData()
    }

    fun save(cat: Cat) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) { catDao.insert(cat) }
        }
    }

    fun update(cat: Cat) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) { catDao.update(cat) }
        }
    }

    fun delete(cat: Cat) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) { catDao.delete(cat) }
        }
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(private val catDao: CatDao) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CatsViewModel::class.java)) {

                return CatsViewModel(catDao) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}