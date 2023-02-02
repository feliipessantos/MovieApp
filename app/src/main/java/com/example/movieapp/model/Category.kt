package com.example.movieapp.model

import com.google.gson.annotations.SerializedName

data class Category (
    @SerializedName("titulo") val title: String? = null,
    @SerializedName("capas") val movies: MutableList<Movie> = mutableListOf()
)

data class Movie(
    @SerializedName("url_imagem") val poster: String? = null,
    @SerializedName("id") var id: Int = 0
)

data class Categories(@SerializedName("categoria")
    val categories: MutableList<Category> = mutableListOf()
)
