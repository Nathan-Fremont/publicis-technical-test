package com.example.publicistechnicaltest.repository

import com.example.publicistechnicaltest.repository.api.model.BookApi
import com.example.publicistechnicaltest.repository.api.model.CommercialOfferListApi
import io.reactivex.Single

interface ApiDataSource {
    fun getBookList(): Single<List<BookApi>>

    fun getCommercialOffers(booksIsbn: String): Single<CommercialOfferListApi>
}