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
import com.challenge.adidas.R
import com.challenge.adidas.common.*
import com.challenge.adidas.presentation.ProductViewModel
import kotlinx.android.synthetic.main.details_screen.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class DetailsScreen : BaseFragment(R.layout.details_screen) {
    private val viewmodel: ProductViewModel by sharedViewModel()
    private val argument: DetailsScreenArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.details_screen, container, false).apply {
            sharedElementReturnTransition =
                TransitionInflater.from(context).inflateTransition(R.transition.move)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel.productDetailsRequested(argument.productId)

        val adapter = ReviewAdapter()
        detailsReviewRecycler.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        detailsReviewRecycler.adapter = adapter

        detilsAddReview.setOnClickListener {
            findNavController().navigate(
                DetailsScreenDirections.actionDetailsScreenToAddReviewScreen(argument.productId)
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