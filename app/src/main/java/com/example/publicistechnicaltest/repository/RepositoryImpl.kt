package com.example.publicistechnicaltest.repository

import com.example.publicistechnicaltest.domain.Repository
import com.example.publicistechnicaltest.domain.model.Book
import com.example.publicistechnicaltest.domain.model.CommercialOffer
import com.example.publicistechnicaltest.repository.mapper.BookListMapper
import com.example.publicistechnicaltest.repository.mapper.CommercialOfferListMapper
import io.reactivex.Single

class RepositoryImpl(
    private val apiDataSource: ApiDataSource,
    private val bookListMapper: BookListMapper,
    private val commercialOfferListMapper: CommercialOfferListMapper
) : Repository {
    override fun getBookList(): Single<List<Book>> {
        return apiDataSource.getBookList()
            .map { bookListApi ->
                bookListApi.map { bookApi ->
                    bookListMapper.toDomain(bookApi)
                }
            }
    }

    override fun getCommercialOffers(booksIsbn: String): Single<List<CommercialOffer>> {
        return apiDataSource.getCommercialOffers(booksIsbn)
            .map { offerListApi ->
                offerListApi.offers.map { commercialOfferApi ->
                    commercialOfferListMapper.toDomain(commercialOfferApi)
                }
            }
    }
}