package com.example.films.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.films.R
import com.example.films.data.model.FilmModel

class FilmsAdapter(private val filmsList: List<FilmModel>): RecyclerView.Adapter<FilmsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return FilmsViewHolder(layoutInflater.inflate(R.layout.item_film, parent, false))
    }

    override fun getItemCount(): Int = filmsList.size

    override fun onBindViewHolder(holder: FilmsViewHolder, position: Int) {
        val item = filmsList[position]
        holder.render(item)
    }

}