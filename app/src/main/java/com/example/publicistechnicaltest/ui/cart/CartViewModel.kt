package com.example.publicistechnicaltest.ui.cart

import com.example.publicistechnicaltest.domain.model.CommercialOfferType
import com.example.publicistechnicaltest.domain.usecase.GetCommercialOffersUseCase
import com.example.publicistechnicaltest.ui.cart.mapper.CommercialOfferUiMapper
import com.example.publicistechnicaltest.ui.cart.model.PageCartUi
import com.example.publicistechnicaltest.ui.common.BaseViewModel
import com.example.publicistechnicaltest.ui.show_book_list.model.BookUi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class CartViewModel(
    private val getCommercialOffersUseCase: GetCommercialOffersUseCase,
    private val commercialOfferUiMapper: CommercialOfferUiMapper
) :
    BaseViewModel<PageCartUi>() {

    fun getCommercialOffersForBooks(books: List<BookUi>) {
        val booksIsbn = books.joinToString(separator = ",") { it.isbn }
        Timber.d("ISBN is $booksIsbn")
        getCommercialOffersUseCase
            .invoke(
                booksIsbn
            )
            .subscribeOn(Schedulers.io())
            .map { commercialOffersList ->
                commercialOffersList.map { commercialOffer ->
                    commercialOfferUiMapper.toUi(commercialOffer)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { commercialOffers ->
                    val rawTotal = books.sumByDouble { it.price }
                    Timber.d("Total for books = $rawTotal")
                    val percentageOffer =
                        commercialOffers.find { it.type == CommercialOfferType.PERCENTAGE }
                    val minusOffer = commercialOffers.find { it.type == CommercialOfferType.MINUS }
                    val sliceOffer = commercialOffers.find { it.type == CommercialOfferType.SLICE }

                    val percentagePrice =
                        if (percentageOffer?.value != null) rawTotal - percentageOffer.value.times(
                            rawTotal
                        ).div(100.0) else null
                    Timber.d("percentagePrice = $percentagePrice")

                    val minusPrice =
                        if (minusOffer?.value != null) rawTotal - minusOffer.value else null
                    Timber.d("minusPrice = $minusPrice")

                    val slicePrice =
                        if (sliceOffer?.sliceValue != null) rawTotal - ((rawTotal / sliceOffer.sliceValue).toInt() * sliceOffer.value).toDouble() else null
                    Timber.d("slicePrice = $slicePrice")

                    val bestCalculatedPrice =
                        listOfNotNull(rawTotal, percentagePrice, minusPrice, slicePrice).min()!!
                    Timber.d("bestPrice = $bestCalculatedPrice")

                    val bestOffer = when {
                        percentagePrice == bestCalculatedPrice -> {
                            percentageOffer
                        }
                        minusPrice == bestCalculatedPrice -> {
                            minusOffer
                        }
                        slicePrice == bestCalculatedPrice -> {
                            sliceOffer
                        }
                        else -> {
                            null
                        }
                    }

                    val newUiData = PageCartUi(
                        rawTotal,
                        bestCalculatedPrice,
                        bestOffer,
                        percentagePrice,
                        minusPrice,
                        slicePrice
                    )
                    postUiData(newUiData)
                }
            )
    }


}