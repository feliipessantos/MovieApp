package com.example.movieapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.MovieItemBinding
import com.example.movieapp.model.Movie

class MovieAdapter(private val context: Context, private val movieList: MutableList<Movie>) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemList = MovieItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return MovieViewHolder(itemList)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.poster.setImageResource(movieList[position].poster!!)
    }

    override fun getItemCount() = movieList.size


    inner class MovieViewHolder(binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val poster = binding.poster


    }
}