package com.example.openbankchallenge.model.character

data class Series(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemXX>,
    val returned: Int
)