package com.example.publicistechnicaltest.repository.api.retrofit

import com.example.publicistechnicaltest.repository.api.model.BookApi
import com.example.publicistechnicaltest.repository.api.model.CommercialOfferListApi
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("books")
    fun getBookList(): Single<List<BookApi>>

    @GET("books/{books_isbn}/commercialOffers")
    fun getCommercialOffers(@Path(value = "books_isbn", encoded = true) booksIsbn: String): Single<CommercialOfferListApi>
}