package com.example.films.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.films.data.model.FilmModel
import com.example.films.domain.GetFilmsUseCase
import kotlinx.coroutines.launch

class FilmViewModel: ViewModel() {
    val films = MutableLiveData<List<FilmModel>>()
    val isLoading = MutableLiveData<Boolean>()

    var getFilmsUseCase = GetFilmsUseCase()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getFilmsUseCase()

            if(result.isNotEmpty()){
                films.postValue(result)
                isLoading.postValue(false)
            }
        }
    }
}