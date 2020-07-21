package com.example.publicistechnicaltest.ui.di

import com.example.publicistechnicaltest.ui.show_book_list.BookListViewModel
import com.example.publicistechnicaltest.ui.show_book_list.mapper.BookUiMapper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiSearchImageViewModelModule = module {
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

val koinUiModules = listOf(
    uiSearchImageViewModelModule
)