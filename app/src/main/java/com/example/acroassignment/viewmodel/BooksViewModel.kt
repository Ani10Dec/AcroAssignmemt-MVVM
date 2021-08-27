package com.example.acroassignment.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.acroassignment.model.BooksModel.Book
import com.example.acroassignment.repository.repository.Repository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class BooksViewModel(private val repository: Repository) : ViewModel() {

    private val allBooks = MutableLiveData<Book>()

    val bookResponse: LiveData<Book>
        get() = allBooks

    init {
        getAllBooks()
    }

    private fun getAllBooks() = viewModelScope.launch(IO) {
        repository.getBooks().let {response ->
            if (response.isSuccessful){
                allBooks.postValue(response.body())
            } else{
                Log.e("Response Error", "getAllBooks: ${response.code()}" )
            }
        }
    }
}