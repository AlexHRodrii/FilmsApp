package com.example.films.data.network

import com.example.films.core.RetrofitHelper
import com.example.films.data.model.FilmModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class FilmService {

    // Instancia de Retrofit
    private val retrofit = RetrofitHelper.getRetrofit()

    // Método para obtener todas las películas
    suspend fun getFilms(): List<FilmModel> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(FilmAPIClient::class.java).getFilms()
            response.body() ?: emptyList()
        }
    }

}