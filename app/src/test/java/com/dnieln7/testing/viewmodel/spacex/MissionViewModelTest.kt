package com.dnieln7.testing.viewmodel.spacex

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dnieln7.testing.datasource.spacex.mission.FakeMissionLocalDataSource
import com.dnieln7.testing.datasource.spacex.mission.FakeMissionRemoteDataSource
import com.dnieln7.testing.repository.spacex.MissionRepository
import com.dnieln7.testing.utils.ApiResponse
import com.dnieln7.testing.utils.TestCoroutineRule
import com.dnieln7.testing.utils.getOrAwaitValue
import com.dnieln7.testing.viewmodel.MissionViewModel
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule

import org.junit.Test

@ExperimentalCoroutinesApi
class MissionViewModelTest {

    private lateinit var missionViewModel: MissionViewModel

    @get:Rule
    var testCoroutineRule = TestCoroutineRule()

    // Everything runs on the same thread one step a the time
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        missionViewModel = MissionViewModel(
            MissionRepository(
                FakeMissionRemoteDataSource(),
                FakeMissionLocalDataSource(),
            )
        )
    }

    @Test
    fun fetchMissions() {
        val result = missionViewModel.apiResponse.getOrAwaitValue()

        assertThat(result).isEqualTo(ApiResponse.Success)
    }

    @Test
    fun getMissions() {
        val result = missionViewModel.getMissions().getOrAwaitValue()

        assertThat(result).isNotEmpty()
    }
}