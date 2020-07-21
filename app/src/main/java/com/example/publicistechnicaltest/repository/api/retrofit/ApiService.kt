package com.example.publicistechnicaltest.repository.api.retrofit

import com.example.publicistechnicaltest.repository.api.model.BookApi
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {
    @GET("books")
    fun getBookList(): Single<List<BookApi>>
}