package com.example.films.data

import com.example.films.data.model.FilmModel
import com.example.films.data.model.FilmProvider
import com.example.films.data.network.FilmService
import javax.inject.Inject

class FilmRepository @Inject constructor(private val api: FilmService) {

    suspend fun getFilms(): List<FilmModel> {
        val response = api.getFilms()
        FilmProvider.films = response
        return response
    }
}