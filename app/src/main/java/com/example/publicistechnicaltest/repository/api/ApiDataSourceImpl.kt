package com.example.publicistechnicaltest.repository.api

import com.example.publicistechnicaltest.repository.ApiDataSource
import com.example.publicistechnicaltest.repository.api.model.BookApi
import com.example.publicistechnicaltest.repository.api.model.CommercialOfferListApi
import com.example.publicistechnicaltest.repository.api.retrofit.ApiService
import io.reactivex.Single

class ApiDataSourceImpl (
    private val apiService: ApiService
): ApiDataSource {

    override fun getBookList(): Single<List<BookApi>> {
        return apiService.getBookList()
    }

    override fun getCommercialOffers(booksIsbn: String): Single<CommercialOfferListApi> {
        return apiService.getCommercialOffers(booksIsbn)
    }
}