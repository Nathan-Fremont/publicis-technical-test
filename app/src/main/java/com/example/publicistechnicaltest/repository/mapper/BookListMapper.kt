package com.example.publicistechnicaltest.repository.mapper

import com.example.publicistechnicaltest.domain.model.Book
import com.example.publicistechnicaltest.repository.api.model.BookApi

class BookListMapper : BaseDomainMapper<BookApi, Book>() {
    override fun toDomain(api: BookApi): Book {
        return Book(
            api.isbn,
            api.title,
            api.price,
            api.cover,
            api.synopsis
        )
    }
}