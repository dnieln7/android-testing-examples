package com.dnieln7.testing.ui.retrofit

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.dnieln7.testing.TestingExamplesApplication
import com.dnieln7.testing.databinding.ActivityRetrofitBinding
import com.dnieln7.testing.ui.retrofit.adapter.MarsPhotoAdapter
import com.dnieln7.testing.viewmodel.MarsViewModel

class RetrofitActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRetrofitBinding

    private val marsViewModel by viewModels<MarsViewModel> {
        MarsViewModel.Factory((application as TestingExamplesApplication).serviceLocator.api.marsApi)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRetrofitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        marsViewModel.photos.observe(this) {
            binding.marsPhotos.adapter = MarsPhotoAdapter(it)
        }

        binding.marsPhotos.setHasFixedSize(true)

        marsViewModel.getPhotos()
    }
}