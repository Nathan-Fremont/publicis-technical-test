package com.example.publicistechnicaltest.ui.show_book_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.publicistechnicaltest.R
import com.example.publicistechnicaltest.domain.model.Either
import com.example.publicistechnicaltest.ui.show_book_list.model.BookUi
import com.example.publicistechnicaltest.ui.show_book_list.view.adapter.BookListAdapter
import kotlinx.android.synthetic.main.fragment_book_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class BookListFragment : Fragment() {

    private val viewModel: BookListViewModel by viewModel()
    private val recyclerAdapter: BookListAdapter by lazy {
        BookListAdapter(
            selectedItems
        )
    }
    private val selectedItems: MutableLiveData<ArrayList<BookUi>> = MutableLiveData(ArrayList())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_book_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragment_book_list_recycler_view.apply {
            layoutManager = GridLayoutManager(view.context, 2)
            adapter = recyclerAdapter
        }

        viewModel.uiData.observe(viewLifecycleOwner, Observer { eitherUiData ->
            Timber.d("Got books, will submit them to adapter")
            selectedItems.value?.clear()
            fragment_book_list_refrehs_list.isRefreshing = false

            when (eitherUiData) {
                is Either.Left -> {
                    recyclerAdapter.submitList(eitherUiData.value)
                }
                is Either.Right -> {
                    Toast.makeText(context, eitherUiData.value.error, Toast.LENGTH_LONG).show()
                }
            }
        })

        fragment_book_list_refrehs_list.setOnRefreshListener {
            viewModel.getBookList()
        }

        selectedItems.observe(viewLifecycleOwner, Observer { selectedBooks ->
            val selectedBooksNumber = selectedBooks.count()
            Timber.d("Selected items are not $selectedBooksNumber")
            fragment_book_list_selected_books_number.visibility = if (selectedBooksNumber > 0) View.VISIBLE else View.GONE
            fragment_book_list_selected_books_number.text = "$selectedBooksNumber"
        })

        fragment_book_list_floating_button.setOnClickListener {
            if (selectedItems.value?.isNotEmpty() == true) {
                Timber.d("Selected enough books, redirect to fragment")
                val action =
                    BookListFragmentDirections.actionBookListFragmentToCartFragment(
                        selectedItems.value!!.toTypedArray()
                    )
                findNavController().navigate(action)
            } else {
                Timber.d("No books selected, show toast")
                Toast
                    .makeText(
                        context,
                        getString(R.string.fragment_book_list_error_selected_not_enough),
                        Toast.LENGTH_SHORT
                    )
                    .show()
            }
        }
    }
}