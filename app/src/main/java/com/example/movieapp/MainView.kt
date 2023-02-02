package com.example.movieapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.adapter.CategoryAdapter
import com.example.movieapp.databinding.ActivityMainViewBinding
import com.example.movieapp.model.Category
import com.example.movieapp.model.Movie
import com.google.firebase.auth.FirebaseAuth

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
        getCategory()

        binding.txtsingOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, FormLogin::class.java)
            startActivity(intent)
            finish()
            Toast.makeText(this, "Logout successful", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getCategory(){
        val category1 = Category("Category 1")
        categoryList.add(category1)

        val category2 = Category("Category 2")
        categoryList.add(category2)

        val category3 = Category("Category 3")
        categoryList.add(category3)
    }

}