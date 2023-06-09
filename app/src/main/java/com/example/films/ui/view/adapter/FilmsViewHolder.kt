package com.example.films.ui.view.adapter

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.films.R
import com.example.films.data.model.FilmModel

class FilmsViewHolder(view: View): ViewHolder(view) {
    val filmTitle = view.findViewById<TextView>(R.id.filmName)
    val filmDuration = view.findViewById<TextView>(R.id.filmDuration)
    val filmPoster = view.findViewById<ImageView>(R.id.ivFilm)
    val deleteButton = view.findViewById<Button>(R.id.button)

    fun render(filmModel: FilmModel) {
        filmTitle.text = filmModel.name
        filmDuration.text = filmModel.playtime
        deleteButton.tag = filmModel.name
        Glide.with(filmPoster.context).load(filmModel.poster).into(filmPoster)
    }
}