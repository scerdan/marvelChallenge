package com.example.openbankchallenge.model.character

data class Comics(
    val available: Int,
    val collectionURI: String,
    val items: ArrayList<Item>,
    val returned: Int
)