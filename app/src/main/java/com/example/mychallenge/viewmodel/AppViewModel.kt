package com.example.mychallenge.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mychallenge.model.character.Character
import com.example.mychallenge.model.details.DetailCharacter
import com.example.mychallenge.repository.AppRepo
import com.example.mychallenge.utils.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(private val repo: AppRepo) : ViewModel() {

    private val _listCharacter = MutableStateFlow<ResourceState<Character>>(ResourceState.Loading())
    val listCharacter: StateFlow<ResourceState<Character>> = _listCharacter

    private val _listDetail = MutableStateFlow<ResourceState<DetailCharacter>>(ResourceState.Loading())
    val listDetail : StateFlow<ResourceState<DetailCharacter>> = _listDetail

    init {
        fetch()
    }

    private fun fetch() = viewModelScope.launch {
        callCharacters()
    }

    private suspend fun callCharacters() {
        try {
            val response = repo.getCharacters("1")
            _listCharacter.value = handleResponse(response)
        } catch (t: Throwable) {
            when (t) {
                is IOException -> _listCharacter.value = ResourceState.Error("Error Conection")
                else -> _listCharacter.value = ResourceState.Error("Failed Data Conversion")
            }
        }
    }

    private suspend fun callDetails(id: String = "") {
        try {
            val response = repo.getDetailCharacter(id)
            _listDetail.value = handleResponse(response)
        } catch (t: Throwable) {
            when (t) {
                is IOException -> _listCharacter.value = ResourceState.Error("Error Conection")
                else -> _listCharacter.value = ResourceState.Error("Failed Data Conversion")
            }
        }
    }

    private fun <T> handleResponse(response: Response<T>): ResourceState<T> {
        if (response.isSuccessful) {
            response.body()?.let { values ->
                return ResourceState.Success(values)
            }
        }
        return ResourceState.Error(response.message())
    }
}