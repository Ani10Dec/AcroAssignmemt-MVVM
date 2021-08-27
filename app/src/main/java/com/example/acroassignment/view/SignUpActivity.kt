package com.example.acroassignment.view

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.acroassignment.R
import com.example.acroassignment.databinding.ActivitySignUpBinding
import com.example.acroassignment.utils.AppHelper
import com.example.acroassignment.viewmodel.SignUpViewModel

class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding
    lateinit var viewModel: SignUpViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)
        binding.signUpViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.message.observe(this, {
            it.getContentIfNotHandledOrReturnNull()?.let { message ->
                if (message == "Registered Successfully") {
                    intentToMainActivity()
                }
                Toast.makeText(this, message, Toast.LENGTH_LONG).show()
            }
        })

        binding.loginBtn.setOnClickListener {
            val intent = Intent(this, LogInActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        binding.signupBtn.setOnClickListener {
            if (AppHelper.isOnline(this)) {
                if (checkForValidation()) {
                    viewModel.registerNewUser()
                }
            } else
                Toast.makeText(
                    this,
                    "No Internet!\nCheck The Connection",
                    Toast.LENGTH_SHORT
                ).show()
        }
    }

    private fun intentToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    private fun checkForValidation(): Boolean {
        if (binding.etEmail.text.isNullOrEmpty()) {
            Toast.makeText(this, "Please enter email id", Toast.LENGTH_SHORT).show()
            return false
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(binding.etEmail.text!!).matches()) {
            Toast.makeText(this, "Please enter correct email id", Toast.LENGTH_SHORT).show()
            return false
        }

        if (binding.etPassword.text.isNullOrEmpty()) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show()
            return false
        }

        if (binding.etConfirmPassword.text.isNullOrEmpty()) {
            Toast.makeText(this, "Please enter confirm password", Toast.LENGTH_SHORT).show()
            return false
        }

        if (binding.etConfirmPassword.text.toString() != binding.etPassword.text.toString()) {
            Toast.makeText(this, "Password mismatch", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}