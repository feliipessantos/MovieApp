package com.example.movieapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.MovieDetails
import com.example.movieapp.databinding.MovieItemBinding
import com.example.movieapp.model.Movie

class MovieAdapter(private val context: Context, private val movieList: MutableList<Movie>) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemList = MovieItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return MovieViewHolder(itemList)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        Glide.with(context).load(movieList[position].poster).centerCrop().into(holder.poster)
        holder.poster.setOnClickListener {
        val intent = Intent(context,MovieDetails::class.java)
            intent.putExtra("poster", movieList[position].poster)
            intent.putExtra("name", movieList[position].nome)
            intent.putExtra("description", movieList[position].descricao)
            intent.putExtra("cast", movieList[position].elenco)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = movieList.size


    inner class MovieViewHolder(binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val poster = binding.poster


    }
}