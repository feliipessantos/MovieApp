package com.example.movieapp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.adapter.CategoryAdapter
import com.example.movieapp.api.Api
import com.example.movieapp.databinding.ActivityMainViewBinding
import com.example.movieapp.model.Categories
import com.example.movieapp.model.Category
import com.example.movieapp.model.Movie
import com.google.firebase.auth.FirebaseAuth
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainView : AppCompatActivity() {
    private lateinit var binding: ActivityMainViewBinding
    private lateinit var categoryAdapter: CategoryAdapter
    private val categoryList: MutableList<Category> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()
        window.statusBarColor = Color.parseColor("#000000")

        val recyclerViewMovies = binding.recyclerViewMovies
        recyclerViewMovies.layoutManager = LinearLayoutManager(this)
        recyclerViewMovies.setHasFixedSize(true)
        categoryAdapter = CategoryAdapter(this, categoryList)
        recyclerViewMovies.adapter = categoryAdapter


        binding.txtsingOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, FormLogin::class.java)
            startActivity(intent)
            finish()
            Toast.makeText(this, "Logout successful", Toast.LENGTH_SHORT).show()
        }

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://stackmobile.com.br/")
            .build()
            .create(Api::class.java)

        retrofit.categoryList().enqueue(object : Callback<Categories>{
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<Categories>, response: Response<Categories>) {
                if(response.code() == 200){
                    response.body()?.let {
                        categoryAdapter.categoryList.addAll(it.categories)
                        categoryAdapter.notifyDataSetChanged()
                        binding.containerProgressBar.visibility = View.GONE
                        binding.progessBar.visibility = View.GONE
                        binding.txtLoading.visibility = View.GONE
                    }
                }
            }

            override fun onFailure(call: Call<Categories>, t: Throwable) {
                Toast.makeText(applicationContext, "Error",Toast.LENGTH_SHORT).show()
            }

        })
    }
}