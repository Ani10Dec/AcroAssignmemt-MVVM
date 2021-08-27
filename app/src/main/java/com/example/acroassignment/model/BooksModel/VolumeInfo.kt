package com.example.acroassignment.model.BooksModel

data class VolumeInfo(
    val authors: List<String>,
    val description: String,
    val imageLinks: ImageLinks,
    val infoLink: String,
    val language: String,
    val pageCount: Int,
    val publishedDate: String,
    val publisher: String,
    val subtitle: String,
    val title: String
)