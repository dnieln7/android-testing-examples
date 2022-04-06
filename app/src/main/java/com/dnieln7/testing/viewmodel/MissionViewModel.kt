package com.dnieln7.testing.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dnieln7.testing.model.spacex.Mission
import com.dnieln7.testing.repository.spacex.MissionRepository
import com.dnieln7.testing.utils.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MissionViewModel @Inject constructor(
    private val missionRepository: MissionRepository
) : ViewModel() {

    private val _apiResponse = MutableLiveData<ApiResponse>()
    val apiResponse: LiveData<ApiResponse> = _apiResponse

    init {
        fetchMissions()
    }

    fun fetchMissions() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) { missionRepository.fetchMissions() }

            _apiResponse.value = result
        }
    }

    fun getMissions(): LiveData<List<Mission>> {
        return missionRepository.getMissions()
    }
}