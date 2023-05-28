package com.example.films.data

import android.content.Context
import android.util.Log
import androidx.lifecycle.asLiveData
import com.example.films.Films
import com.example.films.data.model.FilmModel
import com.example.films.data.model.FilmProvider
import com.example.films.data.network.FilmService
import kotlinx.coroutines.flow.*
import java.io.IOException
import javax.inject.Inject

class FilmRepository @Inject constructor(
    private val api: FilmService,
    private val context: Context
) {

    private val storedFilmsFlow: Flow<Films> = context.filmsDataStore.data
        .catch { exception ->
            // dataStore.data throws an IOException when an error is encountered when reading data
            if (exception is IOException) {
                Log.e("TAG", "Error reading sort order preferences.", exception)
                emit(Films.getDefaultInstance())
            } else {
                throw exception
            }
        }

    private suspend fun updateFilms(filmTitles: List<FilmModel>) {
        context.filmsDataStore.updateData { store ->
            store.toBuilder().clearFilmTitles().build()
        }

        filmTitles.forEach {film ->
            context.filmsDataStore.updateData { store ->
                store.toBuilder().addFilmTitles(film.name).build()
            }
        }
    }

    suspend fun getFilms(): List<FilmModel> {
        val response = api.getFilms()
        val storedFilmsList = storedFilmsFlow.asLiveData().value
        return if (storedFilmsList !== null) {
            val filmsList = response.filter { film -> storedFilmsList.filmTitlesList.contains(film.name) }
            updateFilms(filmsList)
            FilmProvider.films = filmsList
            filmsList
        } else {
            updateFilms(response)
            FilmProvider.films = response
            response
        }
    }

    suspend fun deleteFilm(filmTitle: String): List<FilmModel> {
        FilmProvider.films.filter { film -> film.name !== filmTitle }
        updateFilms(FilmProvider.films)
        return FilmProvider.films
    }
}