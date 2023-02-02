package com.example.movieapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.CategoryItemBinding
import com.example.movieapp.model.Category
import com.example.movieapp.model.Movie

class CategoryAdapter(
    private val context: Context,
    private val categoryList: MutableList<Category>

) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolderCategory>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCategory {
        val listItem = CategoryItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolderCategory(listItem)
    }

    override fun onBindViewHolder(holder: ViewHolderCategory, position: Int) {
        holder.title.text = categoryList[position].title
        val category = categoryList[position]

        holder.recyclerViewMovies.adapter = MovieAdapter(context, category.movies)
        holder.recyclerViewMovies.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    override fun getItemCount() = categoryList.size

    inner class ViewHolderCategory(binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val title = binding.txtTitle
        val recyclerViewMovies = binding.recyclerViewMovies

    }
}