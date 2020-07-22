package com.example.publicistechnicaltest.domain.model

data class CommercialOffer(
    val type: CommercialOfferType,
    val value: Double,
    val sliceValue: Double?
)