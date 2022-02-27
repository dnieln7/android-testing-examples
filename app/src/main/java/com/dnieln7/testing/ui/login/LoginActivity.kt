package com.dnieln7.testing.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.widget.doOnTextChanged
import com.dnieln7.testing.R
import com.dnieln7.testing.TestingExamplesApplication
import com.dnieln7.testing.databinding.ActivityLoginBinding
import com.dnieln7.testing.utils.TextValidation
import com.dnieln7.testing.utils.toastLong
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
                } else {
                    binding.error.text = it.error ?: getString(R.string.unknown_error)
                    binding.error.visibility = View.VISIBLE
                }

                loginViewModel.resetState()
            }
        }
    }

    private fun initActions() {
        binding.email.doOnTextChanged { text, _, _, _ ->
            if (TextValidation.isEmail(text.toString())) {
                binding.emailContainer.error = null
            } else {
                binding.emailContainer.error = "Wrong email"
            }
        }

        binding.login.setOnClickListener {
            if (binding.emailContainer.error == null && binding.email.text.toString().isNotBlank()) {
                loginViewModel.login(
                    binding.email.text.toString(),
                    binding.password.text.toString()
                )
            }
        }
    }
}