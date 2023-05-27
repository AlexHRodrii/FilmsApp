package com.example.films.data.network

import com.example.films.data.model.FilmModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class FilmService @Inject constructor(private val api: FilmAPIClient) {

    // Método para obtener todas las películas
    suspend fun getFilms(): List<FilmModel> {
        return withContext(Dispatchers.IO) {
            val response = api.getFilms()
            response.body() ?: emptyList()
        }
    }

}