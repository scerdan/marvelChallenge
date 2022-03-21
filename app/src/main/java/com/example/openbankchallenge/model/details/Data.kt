package com.example.openbankchallenge.model.details

import com.example.openbankchallenge.model.character.Result

data class Data(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: ArrayList<Result>,
    val total: Int
)