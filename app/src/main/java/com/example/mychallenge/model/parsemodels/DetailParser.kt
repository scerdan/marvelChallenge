package com.example.mychallenge.model.parsemodels

import com.example.mychallenge.model.character.Item
import com.example.mychallenge.model.character.Thumbnail
import com.example.mychallenge.model.details.Result

data class DetailParser(
    val name: String,
    var description: String,
    val comics: ArrayList<Item>,
    val image: Thumbnail
) : ArrayList<Result>() {
    init {
        if (description.isEmpty() || description.isBlank()) {
            description = "Description not Found :("
        }
    }
}