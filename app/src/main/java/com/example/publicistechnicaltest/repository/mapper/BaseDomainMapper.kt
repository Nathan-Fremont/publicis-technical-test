package com.example.publicistechnicaltest.repository.mapper

abstract class BaseDomainMapper<API, DOMAIN> {
    abstract fun toDomain(api: API): DOMAIN
}