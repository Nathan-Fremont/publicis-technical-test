package com.example.publicistechnicaltest.ui.di

import com.example.publicistechnicaltest.ui.cart.CartViewModel
import com.example.publicistechnicaltest.ui.cart.mapper.CommercialOfferUiMapper
import com.example.publicistechnicaltest.ui.show_book_list.BookListViewModel
import com.example.publicistechnicaltest.ui.show_book_list.mapper.BookUiMapper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiBookListViewModelModule = module {
    single {
        BookUiMapper()
    }
    viewModel {
        BookListViewModel(
            getBookListUseCase = get(),
            bookUiMapper = get()
        )
    }

}

val uiCartViewModelModule = module {
    single {
        CommercialOfferUiMapper()
    }
    viewModel {
        CartViewModel(
            getCommercialOffersUseCase = get(),
            commercialOfferUiMapper = get()
        )
    }
}

val koinUiModules = listOf(
    uiBookListViewModelModule,
    uiCartViewModelModule
)