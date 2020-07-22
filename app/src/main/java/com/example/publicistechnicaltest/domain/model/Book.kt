package com.example.publicistechnicaltest.domain.model

data class Book(
    val isbn: String,
    val title: String,
    val price: Double,
    val cover: String,
    val synopsis: List<String>
)