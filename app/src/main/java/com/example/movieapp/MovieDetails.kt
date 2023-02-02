package com.example.movieapp

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.movieapp.databinding.ActivityMovieDetailsBinding

class MovieDetails : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailsBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()
        window.statusBarColor = Color.parseColor("#000000")

        val poster = intent.extras?.getString("poster")
        val name = intent.extras?.getString("name")
        val description = intent.extras?.getString("description")
        val cast = intent.extras?.getString("cast")

        Glide.with(this).load(poster).centerCrop().into(binding.posterMovie)
        binding.txtName.text = name
        binding.txtDescription.text = "Description: $description"
        binding.txtCast.text = "Cast: $cast"

    }
}