package com.dnieln7.testing.ui.mars

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.dnieln7.testing.TestingExamplesApplication
import com.dnieln7.testing.databinding.ActivityMarsBinding
import com.dnieln7.testing.ui.mars.adapter.MarsPhotoAdapter
import com.dnieln7.testing.viewmodel.MarsViewModel

class MarsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMarsBinding

    private val marsViewModel by viewModels<MarsViewModel> {
        MarsViewModel.Factory((application as TestingExamplesApplication).serviceLocator.api.marsApi)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMarsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        marsViewModel.photos.observe(this) {
            binding.marsPhotos.adapter = MarsPhotoAdapter(it)
        }

        binding.marsPhotos.setHasFixedSize(true)

        marsViewModel.getPhotos()
    }
}