package com.example.publicistechnicaltest.ui.show_book_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.publicistechnicaltest.R
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
    private val selectedItems = ArrayList<BookUi>()

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
            layoutManager = LinearLayoutManager(view.context)
            adapter = recyclerAdapter
        }

        viewModel.uiData.observe(viewLifecycleOwner, Observer { listImages ->
            Timber.d("Got some images, will submit them to adapter")
            selectedItems.clear()
            recyclerAdapter.submitList(listImages)
        })

        fragment_book_list_floating_button.setOnClickListener {
            if (selectedItems.size > 0) {
                val action =
                    BookListFragmentDirections.actionBookListFragmentToCartFragment(
                        selectedItems.toTypedArray()
                    )
                findNavController().navigate(action)
            } else {
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