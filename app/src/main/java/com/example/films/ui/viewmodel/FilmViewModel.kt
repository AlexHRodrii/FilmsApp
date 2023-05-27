package com.example.films.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.films.data.model.FilmModel
import com.example.films.domain.GetFilmsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilmViewModel @Inject constructor(
    private val getFilmsUseCase: GetFilmsUseCase
): ViewModel() {
    val films = MutableLiveData<List<FilmModel>>()
    val isLoading = MutableLiveData<Boolean>()

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