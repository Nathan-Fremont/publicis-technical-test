package com.example.publicistechnicaltest.repository.api.model

data class BookApi(
    val isbn: String,
    val title: String,
    val price: Int,
    val cover: String,
    val synopsis: List<String>
)