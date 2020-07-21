package com.example.publicistechnicaltest.repository

import com.example.publicistechnicaltest.repository.api.model.BookApi
import io.reactivex.Single

interface ApiDataSource {
    fun getBookList(): Single<List<BookApi>>
}