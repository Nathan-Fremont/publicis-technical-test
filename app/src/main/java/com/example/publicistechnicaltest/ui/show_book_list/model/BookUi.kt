package com.example.publicistechnicaltest.ui.show_book_list.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BookUi(
    val isbn: String,
    val title: String,
    val price: Int,
    val cover: String,
    val synopsis: List<String>
) : Parcelable