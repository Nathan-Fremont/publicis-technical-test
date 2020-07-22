package com.example.publicistechnicaltest.repository.api.model

import com.example.publicistechnicaltest.domain.model.CommercialOfferType

data class CommercialOfferListApi(
    val offers: List<CommercialOfferApi>
)

data class CommercialOfferApi(
    val type: CommercialOfferType,
    val value: Double,
    val sliceValue: Double?
)