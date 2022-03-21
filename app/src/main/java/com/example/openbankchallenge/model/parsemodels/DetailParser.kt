package com.example.openbankchallenge.model.parsemodels

import com.example.openbankchallenge.model.character.Item
import com.example.openbankchallenge.model.character.Thumbnail
import com.example.openbankchallenge.model.details.Result

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