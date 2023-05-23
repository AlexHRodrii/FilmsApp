package com.example.films.domain

import com.example.films.data.FilmRepository
import com.example.films.data.model.FilmModel

class GetFilmsUseCase {
    private val repository = FilmRepository()

    suspend operator fun invoke(): List<FilmModel> = repository.getFilms()
}