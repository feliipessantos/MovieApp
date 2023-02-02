package com.example.movieapp.api

import com.example.movieapp.model.Categories
import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("/filmes")
    fun categoryList() : Call<Categories>
}