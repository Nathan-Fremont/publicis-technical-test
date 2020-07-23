package com.example.publicistechnicaltest.ui.cart.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.publicistechnicaltest.R
import com.example.publicistechnicaltest.ui.show_book_list.model.BookUi
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.holder_cart_list.view.*

class CartListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(bookUi: BookUi) {
        Picasso
            .get()
            .load(bookUi.cover)
            .into(itemView.fragment_cart_list_cover)
        itemView.holder_cart_list_title.text = bookUi.title
        itemView.holder_cart_list_price.text = itemView.context.getString(R.string.common_number_with_money, bookUi.price)
    }
}