package com.example.acroassignment.repository.repository

import com.example.acroassignment.model.BooksModel.Book
import com.example.acroassignment.model.MovieModel.Movie
import com.example.acroassignment.repository.retrofit.ApiServices
import com.example.acroassignment.utils.Constants
import retrofit2.Response

class Repository(private val retrofitApi: ApiServices) {

    suspend fun getBooks(): Response<Book> {
        return retrofitApi.getBooks("Android")
    }

    suspend fun getMovies(): Response<Movie> {
        return retrofitApi.getMovies(Constants.API_KEY)
    }
}