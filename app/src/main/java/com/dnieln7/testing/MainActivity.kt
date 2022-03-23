package com.dnieln7.testing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dnieln7.testing.databinding.ActivityMainBinding
import com.dnieln7.testing.ui.room.RoomActivity
import com.dnieln7.testing.ui.login.LoginActivity
import com.dnieln7.testing.ui.retrofit.RetrofitActivity
import com.dnieln7.testing.ui.navigation.NavigationActivity
import com.dnieln7.testing.ui.work.WorkManagerActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewModel.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.navigation.setOnClickListener {
            startActivity(Intent(this, NavigationActivity::class.java))
        }

        binding.retrofit.setOnClickListener {
            startActivity(Intent(this, RetrofitActivity::class.java))
        }

        binding.room.setOnClickListener {
            startActivity(Intent(this, RoomActivity::class.java))
        }

        binding.workManager.setOnClickListener {
            startActivity(Intent(this, WorkManagerActivity::class.java))
        }
    }
}