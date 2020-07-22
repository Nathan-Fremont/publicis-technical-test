package com.example.publicistechnicaltest.ui.show_book_list.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.publicistechnicaltest.ui.show_book_list.model.BookUi
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.holder_book_list.view.*

class BookListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(bookUi: BookUi, isSelected: Boolean) {
        Picasso
            .get()
            .load(bookUi.cover)
            .into(itemView.holder_book_list_cover)
        itemView.holder_book_list_title.text = bookUi.title
        itemView.holder_book_list_checkbox.isChecked = isSelected
    }

    fun clicked() {
        itemView.holder_book_list_checkbox.isChecked = !itemView.holder_book_list_checkbox.isChecked
    }
}