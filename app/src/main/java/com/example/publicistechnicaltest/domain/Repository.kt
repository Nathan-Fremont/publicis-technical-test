package com.example.publicistechnicaltest.domain

import com.example.publicistechnicaltest.domain.model.Book
import com.example.publicistechnicaltest.domain.model.CommercialOffer
import io.reactivex.Single

interface Repository {
    fun getBookList(): Single<List<Book>>

    fun getCommercialOffers(booksIsbn: String): Single<List<CommercialOffer>>
}