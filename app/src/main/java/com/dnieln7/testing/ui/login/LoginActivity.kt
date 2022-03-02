package com.dnieln7.testing.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.dnieln7.testing.R
import com.dnieln7.testing.TestingExamplesApplication
import com.dnieln7.testing.databinding.ActivityLoginBinding
import com.dnieln7.testing.utils.TextValidation
import com.dnieln7.testing.utils.toastShort

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val loginViewModel by viewModels<LoginViewModel> {
        LoginViewModel.Factory((application as TestingExamplesApplication).serviceLocator.loginRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListeners()
        initActions()
    }

    private fun initListeners() {
        loginViewModel.loginState.observe(this) {
            if (it.loading) {
                binding.login.isEnabled = false
                binding.progress.visibility = View.VISIBLE
            } else {
                binding.login.isEnabled = true
                binding.progress.visibility = View.GONE

                if (it.success) {
                    binding.error.visibility = View.GONE
                    toastShort("Logged in")
                    loginViewModel.resetState()
                }
                if (it.error != null) {
                    binding.error.text = it.error
                    binding.error.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun initActions() {
        binding.login.setOnClickListener {
            if (isFormValid()) {
                loginViewModel.login(
                    binding.email.text.toString(),
                    binding.password.text.toString()
                )
            }
        }
    }

    private fun isFormValid(): Boolean {
        if (TextValidation.isEmail(binding.email.text.toString())) {
            binding.emailContainer.error = null
        } else {
            binding.emailContainer.error = getString(R.string.wrong_email)
        }

        if (binding.password.text.toString().isNotBlank()) {
            binding.passwordContainer.error = null
        } else {
            binding.passwordContainer.error = getString(R.string.wrong_password)
        }

        return binding.emailContainer.error == null && binding.passwordContainer.error == null
    }
}