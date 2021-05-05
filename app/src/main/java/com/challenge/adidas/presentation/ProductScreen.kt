package com.challenge.adidas.presentation

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
import com.challenge.adidas.common.*
import kotlinx.android.synthetic.main.product_screen.*
import kotlinx.android.synthetic.main.search_layout.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class ProductScreen : BaseFragment(R.layout.product_screen) {

    private val viewmodel: ProductViewModel by sharedViewModel()
    private val adapter: ProductAdapter

    init {
        adapter = ProductAdapter { product, imageview ->
            hideKeyboard()
            val extras = FragmentNavigatorExtras(
                imageview to "productImage"
            )
            findNavController().navigate(
                ProductScreenDirections.actionProductScreenToDetailsScreen(
                    product.id
                ), extras
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchEditText.clearFocus()
        productRecyclerView.requestFocus()

        searchEditText.addTextChangedListener {
            if (!it?.trim().isNullOrEmpty())
                viewmodel.userIsSearching(it.toString())
        }
        configRecyclerView()

        viewmodel.productLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Loading -> productLoadingProgress.visible()
                is Loaded -> {
                    productLoadingProgress.gone()
                    adapter.updateAdapter(it.data)
                }
                is Failed -> {
                    productLoadingProgress.gone()
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    fun configRecyclerView(){
        val gridLayoutManger = GridLayoutManager(requireContext(), 2, RecyclerView.VERTICAL, false)
        productRecyclerView.layoutManager = gridLayoutManger
        productRecyclerView.adapter = adapter
    }
}