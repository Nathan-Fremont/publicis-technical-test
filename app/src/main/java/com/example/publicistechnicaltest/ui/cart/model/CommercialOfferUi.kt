package com.example.publicistechnicaltest.ui.cart.model

import com.example.publicistechnicaltest.domain.model.CommercialOfferType

data class CommercialOfferUi(
    val type: CommercialOfferType,
    val value: Double,
    val sliceValue: Double?
)