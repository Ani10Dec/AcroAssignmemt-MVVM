package com.example.acroassignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.acroassignment.eventMessage.Event
import com.example.acroassignment.model.FeedBackModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class FeedbackViewModel : ViewModel() {

    val inputTitle = MutableLiveData<String>()
    val inputDescription = MutableLiveData<String>()
    private var database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private var reference: DatabaseReference = database.getReference("Feedback")

    private val statusMessage = MutableLiveData<Event<String>>()

    val message: LiveData<Event<String>>
        get() = statusMessage

    fun saveFeedBack() {
        val title = inputTitle.value!!
        val description = inputDescription.value!!

        val model = FeedBackModel(title, description)
        val id = reference.push().key
        reference.child(id!!).setValue(model)
        inputTitle.value = ""
        inputDescription.value = ""
        statusMessage.value = Event("Feedback Sent")
    }
}