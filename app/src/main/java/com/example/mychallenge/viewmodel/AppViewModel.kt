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
    private val _response = MutableLiveData<Response<Character>>()
    private val _responseCharacterDetail = MutableLiveData<Response<DetailCharacter>>()
    val loading_State = mutableStateOf(false)


    private val _listCharacter = MutableStateFlow<ResourceState<Character>>(ResourceState.Loading())
    val listCharacter: StateFlow<ResourceState<Character>> = _listCharacter

    init {
        fetch()
    }

    private fun fetch() = viewModelScope.launch {
        safeFetch()
    }

    private suspend fun safeFetch() {

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

    private fun handleResponse(response: Response<Character>): ResourceState<Character> {
        if (response.isSuccessful) {
            response.body()?.let { values ->
                return ResourceState.Success(values)
            }
        }
        return ResourceState.Error(response.message())
    }
}