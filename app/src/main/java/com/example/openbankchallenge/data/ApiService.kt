package com.example.openbankchallenge.data

import com.example.openbankchallenge.BuildConfig
import com.example.openbankchallenge.utils.Constants
import com.example.openbankchallenge.model.character.Character
import com.example.openbankchallenge.model.details.DetailCharacter
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("/v1/public/characters?limit=50")
    suspend fun getAllCharacters(
        @Query("apikey") apikey: String = BuildConfig.PUBLIC_API_KEY,
        @Query("ts") ts: String = Constants.timeStamp,
        @Query("hash") hash: String = Constants.hash(),
        @Query("offset") offset: String
    ): Response<Character>

    @GET("/v1/public/characters/{characterId}")
    suspend fun getCharacterById(
        @Path("characterId") characterId: String,
        @Query("ts") ts: String = Constants.timeStamp,
        @Query("apikey") apikey: String = Constants.API_KEY,
        @Query("hash") hash: String = Constants.hash(),
    ): Response<DetailCharacter>
}