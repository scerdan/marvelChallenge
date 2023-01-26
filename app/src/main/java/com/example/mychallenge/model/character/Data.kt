package com.example.mychallenge.model.character

data class Data(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: ArrayList<Result>,
    val total: Int
)