package com.example.mychallenge.model.character

data class Comics(
    val available: Int,
    val collectionURI: String,
    val items: ArrayList<Item>,
    val returned: Int
)