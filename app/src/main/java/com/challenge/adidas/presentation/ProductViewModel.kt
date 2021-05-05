package com.challenge.adidas.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.challenge.adidas.Product
import com.challenge.adidas.common.*
import com.challenge.adidas.common.errorhandling.ErrorParser
import com.challenge.adidas.usecase.ProductUseCase
import kotlinx.coroutines.launch

class ProductViewModel(
    private val productUseCase: ProductUseCase,
    private val errorParser: ErrorParser
) : BaseViewModel() {
    private val _productsLiveData: MutableLiveData<LoadableData<List<Product>>> =
        MutableLiveData(NotLoaded)
    val productLiveData: LiveData<LoadableData<List<Product>>> = _productsLiveData

    private val _detailsLiveData: MutableLiveData<LoadableData<Product>> =
        SingleLiveEvent()
    val detailsLiveData: LiveData<LoadableData<Product>> = _detailsLiveData

    private val _searchLiveData: MutableLiveData<LoadableData<Product>> =
        SingleLiveEvent()
    val searchLiveData: LiveData<LoadableData<Product>> = _searchLiveData

    init {
        getProducts()
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("mogger", "clear viewmodel")
    }

    fun userIsSearching(text: String) {
        viewModelScope.launch {
            runCatching {
                productUseCase.getProductsBySearch(text)
            }.fold(onSuccess = {
                _productsLiveData.value = Loaded(it)
            }, onFailure = {
                _productsLiveData.value = Failed(it, errorParser.parse(it))
            })
        }
    }

    private fun getProducts() {
        _productsLiveData.value = Loading
        viewModelScope.launch {
            runCatching {
                productUseCase.getProducts()
            }.fold(onSuccess = {
                _productsLiveData.value = Loaded(it)
            }, onFailure = {
                _productsLiveData.value = Failed(it, errorParser.parse(it))
            })
        }
    }

    fun getDetails(id: String) {
        _detailsLiveData.value = Loading
        viewModelScope.launch {
            runCatching {
                productUseCase.getProductById(id)
            }.fold(onSuccess = {
                _detailsLiveData.value = Loaded(it)
            }, onFailure = {
                _detailsLiveData.value = Failed(it, errorParser.parse(it))
            })
        }
    }
}