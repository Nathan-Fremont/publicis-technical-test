package com.example.publicistechnicaltest.repository

import com.example.publicistechnicaltest.domain.Repository
import com.example.publicistechnicaltest.domain.model.Book
import com.example.publicistechnicaltest.repository.mapper.BookListMapper
import io.reactivex.Single

class RepositoryImpl(
    private val apiDataSource: ApiDataSource,
    private val bookListMapper: BookListMapper
) : Repository {
    override fun getBookList(): Single<List<Book>> {
        return apiDataSource.getBookList()
            .map { bookListApi ->
                bookListApi.map { bookApi ->
                    bookListMapper.toDomain(bookApi)
                }
            }
    }
}