package com.example.acroassignment.repository.retrofit

import androidx.lifecycle.LiveData
import com.example.acroassignment.model.BooksModel.Book
import com.example.acroassignment.model.MovieModel.Movie
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("books/v1/volumes")
    suspend fun getBooks(@Query("q") book: String): Response<Book>

    @GET("3/movie/popular")
    suspend fun getMovies(@Query("api_key") key: String): Response<Movie>

}