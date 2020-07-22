package com.example.publicistechnicaltest.repository.mapper

import com.example.publicistechnicaltest.domain.model.CommercialOffer
import com.example.publicistechnicaltest.repository.api.model.CommercialOfferApi

class CommercialOfferListMapper : BaseDomainMapper<CommercialOfferApi, CommercialOffer>() {
    override fun toDomain(api: CommercialOfferApi): CommercialOffer {
        return CommercialOffer(
            api.type,
            api.value,
            api.sliceValue
        )
    }
}