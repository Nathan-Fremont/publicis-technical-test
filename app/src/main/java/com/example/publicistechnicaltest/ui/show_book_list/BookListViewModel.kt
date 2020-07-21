package com.example.publicistechnicaltest.ui.show_book_list

import com.example.publicistechnicaltest.domain.usecase.GetBookListUseCase
import com.example.publicistechnicaltest.ui.common.BaseViewModel
import com.example.publicistechnicaltest.ui.show_book_list.mapper.BookUiMapper
import com.example.publicistechnicaltest.ui.show_book_list.model.BookUi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class BookListViewModel(
    private val getBookListUseCase: GetBookListUseCase,
    private val bookUiMapper: BookUiMapper
) : BaseViewModel<List<BookUi>>(){

    init {
        getBookListUseCase()
            .subscribeOn(Schedulers.io())
            .map { bookList ->
                bookList.map { book ->
                    bookUiMapper.toUi(book)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    postUiData(it)
                }
            )
    }
}