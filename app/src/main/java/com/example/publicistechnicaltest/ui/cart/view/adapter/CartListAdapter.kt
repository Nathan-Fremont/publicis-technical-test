package com.example.publicistechnicaltest.ui.cart.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.publicistechnicaltest.R
import com.example.publicistechnicaltest.ui.cart.view.CartListViewHolder
import com.example.publicistechnicaltest.ui.show_book_list.model.BookUi
import timber.log.Timber

class CartListAdapter : ListAdapter<BookUi, CartListViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartListViewHolder {
        val view =
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.holder_cart_list, parent, false)
        val holder = CartListViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: CartListViewHolder, position: Int) {
        val bookUi = getItem(position)
        Timber.d("Binding data for $position")
        holder.bind(
            bookUi
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