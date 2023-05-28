package com.example.films.domain

import com.example.films.data.FilmRepository
import com.example.films.data.model.FilmModel
import javax.inject.Inject

class DeleteFilmsUseCase @Inject constructor(private val repository: FilmRepository) {
    suspend operator fun invoke(s: kotlin.String) = repository.deleteFilm(s)
}