package com.example.publicistechnicaltest.domain.usecase

import com.example.publicistechnicaltest.domain.Repository
import com.example.publicistechnicaltest.domain.model.CommercialOffer
import io.reactivex.Single

class GetCommercialOffersUseCase(private val repository: Repository) {
    operator fun invoke(booksIsbn: String): Single<List<CommercialOffer>> {
        return repository.getCommercialOffers(booksIsbn)
    }
}