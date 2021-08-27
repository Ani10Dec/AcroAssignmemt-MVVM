package com.example.acroassignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.acroassignment.eventMessage.Event
import com.google.firebase.auth.FirebaseAuth

class SignUpViewModel : ViewModel() {

    val inputFName = MutableLiveData<String>()
    val inputLName = MutableLiveData<String>()
    val inputEmail = MutableLiveData<String>()
    val inputPassword = MutableLiveData<String>()
    val inputConfirmPassword = MutableLiveData<String>()

    private val statusMessage = MutableLiveData<Event<String>>()

    val message: LiveData<Event<String>>
        get() = statusMessage

    fun registerNewUser() {
        val email = inputEmail.value!!
        val password = inputPassword.value!!

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
//                    val fireBaseUser: FirebaseUser = it.result!!.user!!
                    statusMessage.value = Event("Registered Successfully")
                } else {
                    statusMessage.value = Event(it.exception.toString())
                }
            }
    }
}

