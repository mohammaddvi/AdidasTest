package com.challenge.adidas.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.challenge.adidas.R
import com.challenge.adidas.common.Failed
import com.challenge.adidas.common.Loaded
import kotlinx.android.synthetic.main.product_screen.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProductScreen : Fragment(R.layout.product_screen) {

    private val viewmodel :ProductViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ProductAdapter {

        }
        val gridLayoutManager = LinearLayoutManager(requireContext(),RecyclerView.HORIZONTAL,false)
        productRecyclerView.layoutManager = gridLayoutManager
        productRecyclerView.adapter = adapter

        viewmodel.productLiveData.observe(viewLifecycleOwner, Observer {
            when(it){
                is Loaded -> adapter.updateAdapter(it.data)
                is Failed -> Toast.makeText(requireContext(),"problem",Toast.LENGTH_LONG).show()
            }
        })
    }
}