package com.example.movieapp.model

data class Category (
    val title: String? = null,
    val movies: MutableList<Movie> = mutableListOf()
)

data class Movie(
    val poster: Int? = null
)