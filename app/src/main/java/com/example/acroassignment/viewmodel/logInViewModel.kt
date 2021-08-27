package com.example.acroassignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.acroassignment.eventMessage.Event
import com.google.firebase.auth.FirebaseAuth

class LogInViewModel : ViewModel() {

    val inputEmail = MutableLiveData<String>()
    val inputPassword = MutableLiveData<String>()
    private val statusMessage = MutableLiveData<Event<String>>()

    val message: LiveData<Event<String>>
        get() = statusMessage


    fun logInUser() {
        val email = inputEmail.value!!
        val password = inputPassword.value!!

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    statusMessage.value = Event("User logged in successful")
                } else {
                    statusMessage.value = Event("${it.exception}")
                }
            }
    }
}