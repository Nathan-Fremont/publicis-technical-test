package com.example.publicistechnicaltest.ui.show_book_list.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.publicistechnicaltest.R
import com.example.publicistechnicaltest.ui.show_book_list.model.BookUi
import com.example.publicistechnicaltest.ui.show_book_list.view.BookListViewHolder
import timber.log.Timber

class BookListAdapter(private val selectedItems: ArrayList<BookUi>) : ListAdapter<BookUi, BookListViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookListViewHolder {
        val view =
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.holder_book_list, parent, false)
        val holder = BookListViewHolder(view).apply {
            itemView.setOnClickListener {
                val bookUi = getItem(adapterPosition)
                Timber.d("Click on item at position $adapterPosition")
                if (selectedItems.contains(bookUi)) {
                    selectedItems.remove(bookUi)
                } else {
                    selectedItems.add(bookUi)
                }
                clicked()
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: BookListViewHolder, position: Int) {
        val bookUi = getItem(position)
        val isSelected = selectedItems.contains(bookUi)
        Timber.d("isSelected = $isSelected")
        holder.bind(
            bookUi,
            isSelected
        )
    }

    override fun getItemId(position: Int): Long {
        val bookUi = getItem(position)
        return bookUi.hashCode().toLong()
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<BookUi>() {
            override fun areItemsTheSame(
                oldItem: BookUi,
                newItem: BookUi
            ): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }

            override fun areContentsTheSame(
                oldItem: BookUi,
                newItem: BookUi
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}