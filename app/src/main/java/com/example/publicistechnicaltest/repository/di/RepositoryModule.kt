package com.example.publicistechnicaltest.repository.di

import com.example.publicistechnicaltest.domain.Repository
import com.example.publicistechnicaltest.repository.ApiDataSource
import com.example.publicistechnicaltest.repository.RepositoryImpl
import com.example.publicistechnicaltest.repository.api.ApiDataSourceImpl
import com.example.publicistechnicaltest.repository.api.retrofit.ApiRetrofitFactory
import com.example.publicistechnicaltest.repository.mapper.BookListMapper
import com.example.publicistechnicaltest.repository.mapper.CommercialOfferListMapper
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val apiRetrofitModule = module {
    single {
        ApiRetrofitFactory(androidContext()).buildApiService(
            ApiRetrofitFactory(androidContext()).buildApiRetrofit()
        )
    }
}

val apiDataSourceModule = module {
    single {
        ApiDataSourceImpl(
            apiService = get()
        ) as ApiDataSource
    }
}

val repositoryModule = module {
    single {
        RepositoryImpl(
            apiDataSource = get(),
            bookListMapper = get(),
            commercialOfferListMapper = get()
        ) as Repository
    }
}

val apiMapperModule = module {
    single {
        BookListMapper()
    }
    single {
        CommercialOfferListMapper()
    }
}

val koinRepositoryModules = listOf(
    apiRetrofitModule,
    apiDataSourceModule,
    repositoryModule,
    apiMapperModule
)