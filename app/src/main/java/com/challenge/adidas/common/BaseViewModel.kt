package com.challenge.adidas.common

import androidx.lifecycle.ViewModel

abstract class BaseViewModel() : ViewModel() {
    private fun create() {
        onCreate()
    }

    protected open fun onCreate() {

    }
}