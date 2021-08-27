package com.example.acroassignment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.acroassignment.repository.repository.Repository

class ViewModelFactory(private val repository: Repository, private val sources: String) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when (sources) {
            "books" -> return BooksViewModel(repository) as T
            "movies" -> return MoviesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}