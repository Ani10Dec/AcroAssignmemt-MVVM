package com.example.acroassignment.view

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.acroassignment.R
import com.example.acroassignment.databinding.ActivityLogInBinding
import com.example.acroassignment.utils.AppHelper
import com.example.acroassignment.viewmodel.LogInViewModel
import com.google.firebase.auth.FirebaseAuth


class LogInActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLogInBinding
    private lateinit var loginViewModel: LogInViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_log_in)
        loginViewModel = ViewModelProvider(this).get(LogInViewModel::class.java)
        binding.loginViewModel = loginViewModel
        binding.lifecycleOwner = this

        loginViewModel.message.observe(this, {
            it.getContentIfNotHandledOrReturnNull()?.let { message ->
                if (message == "User logged in successful") {
                    intentToMainActivity()
                }
                Toast.makeText(this, message, Toast.LENGTH_LONG).show()
            }
        })

        // Check for existing user
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            intentToMainActivity()
        }

        binding.registerBtn.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        binding.loginBtn.setOnClickListener {
            if (AppHelper.isOnline(this)) {
                if (checkForValidation()) {
                    loginViewModel.logInUser()
                }
            } else {
                Toast.makeText(
                    this,
                    "No Internet!\nCheck The Connection",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }

    private fun checkForValidation(): Boolean {
        if (binding.etEmail.text.isNullOrEmpty()) {
            Toast.makeText(
                this,
                "Please enter email id",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }
        if (binding.etPassword.text.isNullOrEmpty()) {
            Toast.makeText(
                this,
                "Please enter password",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(binding.etEmail.text!!).matches()) {
            Toast.makeText(
                this,
                "Please enter correct email id",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }
        return true
    }

    private fun intentToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}