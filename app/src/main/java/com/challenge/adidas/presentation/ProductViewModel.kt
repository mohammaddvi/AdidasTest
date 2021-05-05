package com.challenge.adidas.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.challenge.adidas.Product
import com.challenge.adidas.Review
import com.challenge.adidas.common.*
import com.challenge.adidas.common.errorhandling.ErrorParser
import com.challenge.adidas.repository.ProductRepository
import com.challenge.adidas.usecase.ProductUseCase
import kotlinx.coroutines.launch

class ProductViewModel(
    private val productUseCase: ProductUseCase,
    private val productRepository: ProductRepository,
    private val errorParser: ErrorParser
) : BaseViewModel() {
    private val _productsLiveData: MutableLiveData<LoadableData<List<Product>>> =
        MutableLiveData(NotLoaded)
    val productLiveData: LiveData<LoadableData<List<Product>>> = _productsLiveData

    private val _detailsLiveData: MutableLiveData<LoadableData<Product>> =
        SingleLiveEvent()
    val detailsLiveData: LiveData<LoadableData<Product>> = _detailsLiveData

    private val _sendReviewLiveData: MutableLiveData<LoadableData<Review>> =
        SingleLiveEvent()
    val sendReviewLiveData: LiveData<LoadableData<Review>> = _sendReviewLiveData

    init {
        getProducts()
    }

    fun userIsSearching(query: String) = searchProduct(query)
    fun sendReviewButtonClicked(id:String,review:Review) = sendReview(id,review)
    fun productDetailsRequested(id:String) = getDetails(id)

    private fun searchProduct(text: String) {
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

    private fun getDetails(id: String) {
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

    private fun sendReview(id: String, review: Review) {
        _sendReviewLiveData.value = Loading
        viewModelScope.launch {
            runCatching {
                productRepository.sendReview(id,review)
            }.fold(onSuccess = {
                _sendReviewLiveData.value = Loaded(it)
            }, onFailure = {
                _sendReviewLiveData.value = Failed(it, errorParser.parse(it))
            })
        }
    }
}