package com.example.publicistechnicaltest.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.publicistechnicaltest.R
import com.example.publicistechnicaltest.databinding.FragmentCartBinding
import com.example.publicistechnicaltest.ui.cart.view.adapter.CartListAdapter
import kotlinx.android.synthetic.main.fragment_cart.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CartFragment : Fragment() {

    private val viewModel: CartViewModel by viewModel()
    private val navArgs: CartFragmentArgs by navArgs()
    private lateinit var binding: FragmentCartBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_cart, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.uiData.observe(viewLifecycleOwner, Observer { cartPageData ->
            fragment_cart_total.text = getString(R.string.common_number_with_money, cartPageData.rawPriceForSelectedBooks)
            fragment_cart_calculated.text = getString(R.string.common_number_with_money, cartPageData.bestPriceForSelectedBooks)
            binding.viewmodel = viewModel
        })

        viewModel.getCommercialOffersForBooks(navArgs.selectedBookList.toList())
        val cartListAdapter = CartListAdapter()
        fragment_cart_recycler_view.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = cartListAdapter
            cartListAdapter.submitList(navArgs.selectedBookList.toList())
        }
    }
}