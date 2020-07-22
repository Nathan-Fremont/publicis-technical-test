package com.example.publicistechnicaltest.domain.model

import com.squareup.moshi.Json

enum class CommercialOfferType {
    @Json(name = "percentage")
    PERCENTAGE,
    @Json(name = "minus")
    MINUS,
    @Json(name = "slice")
    SLICE
}