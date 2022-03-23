package com.dnieln7.testing.ui.work

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.dnieln7.testing.R
import com.dnieln7.testing.databinding.ActivityWorkManagerBinding
import com.dnieln7.testing.viewmodel.WorkViewModel
import com.dnieln7.testing.work.drink.DrinkWorkManager

class WorkManagerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWorkManagerBinding

    private val workViewModel by viewModels<WorkViewModel> {
        WorkViewModel.Factory(DrinkWorkManager(this, allowLowBattery = true))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorkManagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.start.setOnClickListener {
            if (binding.name.text.toString().isNotBlank()) {
                binding.nameContainer.error = null
                workViewModel.startReminder(binding.name.text.toString())
            } else {
                binding.nameContainer.error = getString(R.string.wrong_empty)
            }
        }

        binding.cancel.setOnClickListener {
            workViewModel.cancelReminder()
        }
    }
}