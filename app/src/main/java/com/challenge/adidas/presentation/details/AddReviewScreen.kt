package com.challenge.adidas.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.challenge.adidas.R
import com.challenge.adidas.Review
import com.challenge.adidas.common.Failed
import com.challenge.adidas.common.Loaded
import com.challenge.adidas.presentation.ProductViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.add_review_screen.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class AddReviewScreen : BottomSheetDialogFragment() {

    private val viewmodel: ProductViewModel by sharedViewModel()
    private val argument: AddReviewScreenArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(context)
            .inflate(R.layout.add_review_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addReviewRatingbar.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            addReviewRatingbar.rating = rating
        }

        viewmodel.sendReviewLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Loaded -> {
                    dismiss()
                    Toast.makeText(
                        requireContext(),
                        "Your review is submitted successfully",
                        Toast.LENGTH_LONG
                    ).show()
                }
                is Failed -> {
                    dismiss()
                    Toast.makeText(requireContext(), "Sorry, try again", Toast.LENGTH_LONG).show()
                }
            }
        })

        addReviewSubmitButton.setOnClickListener {
            viewmodel.sendReviewButtonClicked(
                argument.productId,
                Review(
                    productId = argument.productId,
                    locale = "en-US",
                    rating = addReviewRatingbar.rating.toInt(),
                    text = addReviewEdit.text.toString(),
                )
            )
        }
    }
}