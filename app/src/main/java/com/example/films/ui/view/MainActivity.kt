package com.example.films.ui.view

import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.films.data.model.FilmProvider
import com.example.films.databinding.ActivityMainBinding
import com.example.films.ui.view.adapter.FilmsAdapter
import com.example.films.ui.viewmodel.FilmViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val filmViewModel: FilmViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        filmViewModel.onCreate()

        filmViewModel.isLoading.observe(this, Observer {
            binding.progressBar.isVisible = it
        })

        filmViewModel.films.observe(this, Observer {
            binding.recyclerView.layoutManager = GridLayoutManager(this, 3)
            binding.recyclerView.adapter = FilmsAdapter(FilmProvider.films)
        })

    }
}