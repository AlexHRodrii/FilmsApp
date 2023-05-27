package com.example.films.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.films.data.model.FilmModel
import com.example.films.databinding.DetailFilmBinding
import com.bumptech.glide.Glide



class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var binding: DetailFilmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailFilmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val filmModel: FilmModel? = intent.getParcelableExtra("film")

        filmModel?.let {
            binding.FilmName.text = it.name
            binding.FilmRelease.text = it.release
            binding.FilmDuration.text = it.playtime
            binding.FilmDescription.text = it.description
            binding.FilmPlot.text = it.plot

            Glide.with(this)
                .load(it.poster)
                .into(binding.FilmPoster)

            Glide.with(this)
                .asGif()
                .load(it.gif)
                .into(binding.FilmGif)


        }
    }
}

