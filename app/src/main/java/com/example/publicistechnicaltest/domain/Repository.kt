package com.example.publicistechnicaltest.domain

import com.example.publicistechnicaltest.domain.model.Book
import io.reactivex.Single

interface Repository {
    fun getBookList(): Single<List<Book>>
}