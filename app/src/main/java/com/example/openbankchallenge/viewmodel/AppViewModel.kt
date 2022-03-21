package com.example.openbankchallenge.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openbankchallenge.model.character.Character
import com.example.openbankchallenge.model.details.DetailCharacter
import com.example.openbankchallenge.repository.AppRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(private val repo: AppRepo) : ViewModel() {
    private val _response = MutableLiveData<Response<Character>>()
    private val _responseCharacterDetail = MutableLiveData<Response<DetailCharacter>>()
    val loading_State = mutableStateOf(false)

    fun getData(offset: String): LiveData<Response<Character>> {
        loading_State.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val Data = repo.getCharacters(offset)
            try {
                _response.postValue(Data)
                loading_State.value = false

                Log.e(Data.code().toString(), Data.message())

            } catch (e: Exception) {
                loading_State.value = true
                Log.e(TAG, "launchJob: Exception: ${e}, ${e.cause}")
                e.printStackTrace()
            } finally {
                Log.d(TAG, "launchJob: finally called.")
            }
        }
        return _response
    }

    fun getDetailData(id: String): LiveData<Response<DetailCharacter>> {
        viewModelScope.launch(Dispatchers.IO) {
            val characterData = repo.getDetailCharacter(id)
            try {
                _responseCharacterDetail.postValue(characterData)
                Log.e(characterData.code().toString(), characterData.message())
            } catch (e: Exception) {
                Log.e(TAG, "launchJob: Exception: ${e}, ${e.cause}")
                e.printStackTrace()
            } finally {
                Log.d(TAG, "launchJob: finally called.")
            }
        }
        return _responseCharacterDetail
    }
}