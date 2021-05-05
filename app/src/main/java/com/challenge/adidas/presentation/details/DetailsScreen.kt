package com.challenge.adidas.presentation.details

import android.os.Build
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.challenge.adidas.R
import com.challenge.adidas.common.Failed
import com.challenge.adidas.common.Loaded
import com.challenge.adidas.presentation.ProductViewModel
import kotlinx.android.synthetic.main.details_screen.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class DetailsScreen : Fragment(R.layout.details_screen) {
    private val viewmodel: ProductViewModel by sharedViewModel()
    private val argument: DetailsScreenArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState).apply {
            if (Build.VERSION.SDK_INT >= 21) {
                sharedElementEnterTransition =
                    TransitionInflater.from(context).inflateTransition(R.transition.move)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ViewCompat.setTransitionName(detailsItemImage, "image_${argument.productId}")
        viewmodel.getDetails(argument.productId)


        val adapter = ReviewAdapter()
        detailsReviewRecycler.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        detailsReviewRecycler.adapter = adapter

        viewmodel.detailsLiveData.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            when (it) {
                is Loaded -> {
                    detailsNameItemText.text = it.data.name
                    detailsDescriptionItemText.text = it.data.description
                    detailsPriceItemText.text = it.data.price
                    adapter.updateAdapter(it.data.reviews)
                }
                is Failed -> Toast.makeText(requireContext(), "problem", Toast.LENGTH_LONG).show()
            }
        })

    }
}