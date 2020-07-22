package com.example.publicistechnicaltest.domain.usecase

import com.example.publicistechnicaltest.domain.Repository
import com.example.publicistechnicaltest.domain.model.Book
import io.reactivex.Single

class GetBookListUseCase(private val repository: Repository) {
    operator fun invoke(): Single<List<Book>> {
        return repository.getBookList()
    }
}