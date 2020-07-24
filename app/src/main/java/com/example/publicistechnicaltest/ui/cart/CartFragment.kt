package com.example.publicistechnicaltest.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.publicistechnicaltest.R
import com.example.publicistechnicaltest.databinding.FragmentCartBinding
import com.example.publicistechnicaltest.domain.model.CommercialOfferType
import com.example.publicistechnicaltest.domain.model.Either
import com.example.publicistechnicaltest.ui.cart.view.adapter.CartListAdapter
import kotlinx.android.synthetic.main.fragment_cart.*
import okhttp3.internal.format
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

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

        viewModel.uiData.observe(viewLifecycleOwner, Observer { eitherUiData ->

            when (eitherUiData) {
                is Either.Left -> {
                    val cartPageData = eitherUiData.value

                    fragment_cart_raw_total.text = getString(R.string.common_number_with_money, cartPageData.rawPriceForSelectedBooks)

                    val diff = cartPageData.rawPriceForSelectedBooks - cartPageData.bestPriceForSelectedBooks
                    Timber.d("Best offer is ${cartPageData.bestOfferForSelectedBooks?.type}")
                    Timber.d("Diff is $diff")
                    val discountDetails = when (cartPageData.bestOfferForSelectedBooks?.type) {
                        CommercialOfferType.PERCENTAGE -> {
                            "(${cartPageData.bestOfferForSelectedBooks.value} %%)"
                        }
                        CommercialOfferType.MINUS -> {
                            ""
                        }
                        CommercialOfferType.SLICE -> {
                            getString(R.string.fragment_cart_discount_details, cartPageData.bestOfferForSelectedBooks.value, cartPageData.bestOfferForSelectedBooks.sliceValue)
                        }
                        else -> ""
                    }
                    fragment_cart_eligible_discount.text = format("%.2f € $discountDetails", diff)
                    fragment_cart_discount_total.text = getString(R.string.common_number_with_money, cartPageData.bestPriceForSelectedBooks)
                    binding.viewmodel = viewModel
                }
                is Either.Right -> {
                    Toast.makeText(context, eitherUiData.value.error, Toast.LENGTH_LONG).show()
                    parentFragmentManager.popBackStack()
                }
            }

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