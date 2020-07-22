package com.example.publicistechnicaltest.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.publicistechnicaltest.R
import kotlinx.android.synthetic.main.fragment_cart.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CartFragment : Fragment() {

    private val viewModel: CartViewModel by viewModel()
    private val navArgs: CartFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.uiData.observe(viewLifecycleOwner, Observer { cartPageData ->
            fragment_cart_total.text = "${cartPageData.rawPriceForSelectedBooks} €"
            fragment_cart_calculated.text = "${cartPageData.bestPriceForSelectedBooks} €"
        })

        viewModel.getCommercialOffersForBooks(navArgs.selectedBookList.toList())
    }
}