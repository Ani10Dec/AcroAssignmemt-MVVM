package com.example.acroassignment.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.acroassignment.R
import com.example.acroassignment.databinding.ActivityFeedBackBinding
import com.example.acroassignment.utils.AppHelper
import com.example.acroassignment.viewmodel.FeedbackViewModel

class FeedBackActivity : AppCompatActivity() {

    lateinit var binding: ActivityFeedBackBinding
    lateinit var viewModel: FeedbackViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_feed_back)
        viewModel = ViewModelProvider(this).get(FeedbackViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.message.observe(this, {
            it.getContentIfNotHandledOrReturnNull()?.let { message ->
                Toast.makeText(this, message, Toast.LENGTH_LONG).show()
            }
        })

        binding.btnSubmit.setOnClickListener {
            if (AppHelper.isOnline(this)) {
                if (checkForValidation()) {
                    viewModel.saveFeedBack()
                }
            }
        }
    }

    private fun checkForValidation(): Boolean {

        if (binding.etTitle.text.isNullOrEmpty()) {
            Toast.makeText(this, "Please Enter Title", Toast.LENGTH_SHORT).show()
            return false
        }

        if (binding.etDescription.text.isNullOrEmpty()) {
            Toast.makeText(this, "Please Enter Description", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }
}