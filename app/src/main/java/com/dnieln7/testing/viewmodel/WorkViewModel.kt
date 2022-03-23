package com.dnieln7.testing.viewmodel

import androidx.lifecycle.*
import com.dnieln7.testing.work.drink.DrinkWorkManager

class WorkViewModel(private val workManager: DrinkWorkManager) : ViewModel() {

    fun startReminder(name: String) {
        workManager.start(name)
    }

    fun cancelReminder() {
        workManager.cancel()
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(private val workManager: DrinkWorkManager) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return WorkViewModel(workManager) as T
        }
    }
}