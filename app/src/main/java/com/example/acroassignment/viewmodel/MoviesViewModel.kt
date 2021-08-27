package com.example.acroassignment.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.acroassignment.model.MovieModel.Movie
import com.example.acroassignment.repository.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoviesViewModel(private val repository: Repository) : ViewModel() {

    private val allMovies = MutableLiveData<Movie>()

    val moviesResponse: LiveData<Movie>
        get() = allMovies

    init {
        getAllMovies()
    }

    private fun getAllMovies() = viewModelScope.launch(Dispatchers.IO) {
        repository.getMovies().let { response ->
            if (response.isSuccessful) {
                allMovies.postValue(response.body())
            } else {
                Log.e("Response Error", "getAllMovies: ${response.code()}")
            }
        }
    }
}