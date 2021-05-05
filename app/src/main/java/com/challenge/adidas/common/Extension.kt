package com.challenge.adidas.common

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide


fun ImageView.load(url: String?) =
    Glide.with(this).load(url).into(this)

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}
