package com.example.publicistechnicaltest.ui.cart.mapper

import com.example.publicistechnicaltest.domain.model.CommercialOffer
import com.example.publicistechnicaltest.ui.cart.model.CommercialOfferUi
import com.example.publicistechnicaltest.ui.common.BaseUiMapper

class CommercialOfferUiMapper : BaseUiMapper<CommercialOffer, CommercialOfferUi>() {
    override fun toUi(domain: CommercialOffer): CommercialOfferUi {
        return CommercialOfferUi(
            domain.type,
            domain.value,
            domain.sliceValue
        )
    }
}