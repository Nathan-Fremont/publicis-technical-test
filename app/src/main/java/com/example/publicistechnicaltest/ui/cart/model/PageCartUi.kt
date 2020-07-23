package com.example.publicistechnicaltest.ui.cart.model

data class PageCartUi(
    val rawPriceForSelectedBooks: Double,
    val bestPriceForSelectedBooks: Double,
    val bestOfferForSelectedBooks: CommercialOfferUi?,
    val percentagePriceForSelectedBooks: Double?,
    val minusPriceForSelectedBooks: Double?,
    val slicePriceForSelectedBooks: Double?
)