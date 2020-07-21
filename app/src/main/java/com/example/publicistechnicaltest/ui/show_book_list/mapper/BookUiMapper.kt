package com.example.publicistechnicaltest.ui.show_book_list.mapper

import com.example.publicistechnicaltest.domain.model.Book
import com.example.publicistechnicaltest.ui.common.BaseUiMapper
import com.example.publicistechnicaltest.ui.show_book_list.model.BookUi

class BookUiMapper : BaseUiMapper<Book, BookUi>() {
    override fun toUi(domain: Book): BookUi {
        return BookUi(
            domain.isbn,
            domain.title,
            domain.price,
            domain.cover,
            domain.synopsis
        )
    }
}