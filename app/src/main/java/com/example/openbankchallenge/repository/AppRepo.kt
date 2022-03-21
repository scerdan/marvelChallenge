package com.example.openbankchallenge.repository

import com.example.openbankchallenge.data.ApiService
import com.example.openbankchallenge.model.character.Character
import com.example.openbankchallenge.model.details.DetailCharacter
import retrofit2.Response
import javax.inject.Inject

class AppRepo @Inject constructor(private val apiService: ApiService) {
    suspend fun getCharacters(offset: String): Response<Character> {
        return apiService.getAllCharacters(offset = offset)
    }

    suspend fun getDetailCharacter(id: String): Response<DetailCharacter> {
        return apiService.getCharacterById(id)
    }
}