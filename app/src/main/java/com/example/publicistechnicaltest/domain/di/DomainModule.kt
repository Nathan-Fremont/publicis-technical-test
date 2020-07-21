package com.example.publicistechnicaltest.domain.di

import com.example.publicistechnicaltest.domain.usecase.GetBookListUseCase
import org.koin.dsl.module

val domainUseCaseModule = module {
    factory {
        GetBookListUseCase(
            repository = get()
        )
    }
}

val koinDomainModules = listOf(
    domainUseCaseModule
)