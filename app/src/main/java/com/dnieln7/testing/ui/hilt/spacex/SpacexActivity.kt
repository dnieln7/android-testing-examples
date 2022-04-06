package com.dnieln7.testing.ui.hilt.spacex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.dnieln7.testing.databinding.ActivitySpacexBinding
import com.dnieln7.testing.ui.hilt.adapter.MissionAdapter
import com.dnieln7.testing.utils.ApiResponse
import com.dnieln7.testing.utils.toastLong
import com.dnieln7.testing.utils.toastShort
import com.dnieln7.testing.viewmodel.MissionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SpacexActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySpacexBinding

    private lateinit var adapter: MissionAdapter

    private val missionViewModel by viewModels<MissionViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpacexBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = MissionAdapter()

        binding.refresh.setOnRefreshListener { missionViewModel.fetchMissions() }

        binding.missions.setHasFixedSize(true)
        binding.missions.adapter = adapter

        missionViewModel.getMissions().observe(this) {
            binding.refresh.isRefreshing = false
            adapter.submitList(it)
        }

        missionViewModel.apiResponse.observe(this) {
            when (it) {
                is ApiResponse.Error -> toastLong(it.message)
                ApiResponse.Success -> toastShort("Success")
            }
        }
    }
}