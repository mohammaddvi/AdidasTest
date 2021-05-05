package com.challenge.adidas.presentation

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.challenge.adidas.R
import com.challenge.adidas.common.Failed
import com.challenge.adidas.common.Loaded
import kotlinx.android.synthetic.main.item_product.*
import kotlinx.android.synthetic.main.product_screen.*
import kotlinx.android.synthetic.main.search_layout.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class ProductScreen : Fragment(R.layout.product_screen) {

    private val viewmodel: ProductViewModel by sharedViewModel()
    private val adapter: ProductAdapter

    init {
        adapter = ProductAdapter {
            val extras = FragmentNavigatorExtras(
                productItemImage to "image_${it.id}"
            )
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                findNavController().navigate(
                    ProductScreenDirections.actionProductScreenToDetailsScreen(
                        it.id
                    ), extras
                )
            } else {
                findNavController().navigate(
                    ProductScreenDirections.actionProductScreenToDetailsScreen(
                        it.id
                    )
                )
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchEditText.clearFocus()
        productRecyclerView.requestFocus()
        searchEditText.addTextChangedListener {
            viewmodel.userIsSearching(it.toString())
        }
        val gridLayoutManger = GridLayoutManager(requireContext(), 2, RecyclerView.VERTICAL, false)
        productRecyclerView.layoutManager = gridLayoutManger
        productRecyclerView.adapter = adapter

        viewmodel.productLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Loaded -> adapter.updateAdapter(it.data)
                is Failed -> Toast.makeText(requireContext(), "problem", Toast.LENGTH_LONG).show()
            }
        })
    }
}