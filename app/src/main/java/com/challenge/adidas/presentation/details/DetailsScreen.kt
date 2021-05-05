package com.challenge.adidas.presentation.details

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.challenge.adidas.Product
import com.challenge.adidas.R
import com.challenge.adidas.common.*
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
        val view = inflater.inflate(R.layout.details_screen, container, false)
        sharedElementReturnTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailsItemImage.transitionName = argument.product.imgUrl.toString()
        viewmodel.productDetailsRequested(argument.product.id)


        val adapter = ReviewAdapter()
        detailsReviewRecycler.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        detailsReviewRecycler.adapter = adapter

        detilsAddReview.setOnClickListener {
            findNavController().navigate(
                DetailsScreenDirections.actionDetailsScreenToAddReviewScreen(argument.product.id)
            )
        }

        viewmodel.detailsLiveData.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            when (it) {
                is Loading ->
                    detailsReviewLoadingProgress.visible()

                is Loaded -> {
                    detailsReviewLoadingProgress.gone()
                    detailsNameItemText.text = it.data.name
                    detailsDescriptionItemText.text = it.data.description
                    detailsItemImage.load(it.data.imgUrl)
                    detailsPriceItemText.text = it.data.price
                    adapter.updateAdapter(it.data.reviews)
                }
                is Failed -> {
                    detailsReviewLoadingProgress.gone()
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
            }
        })


    }
}