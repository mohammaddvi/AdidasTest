package com.challenge.adidas.presentation

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
//    coroutineDispatcherProvider: CoroutineDispatcherProvider
) : BaseViewModel() {
    private val _productsLiveData: MutableLiveData<LoadableData<List<Product>>> =
        MutableLiveData(NotLoaded)
    val productLiveData: LiveData<LoadableData<List<Product>>> = _productsLiveData

    init {
        getProducts()
    }

    private fun getProducts() {
        viewModelScope.launch {
            _productsLiveData.value = Loading
            runCatching {
                productUseCase.getProducts()
            }.fold(onSuccess = {
                _productsLiveData.value = Loaded(it)
            }, onFailure = {
                _productsLiveData.value = Failed(it,errorParser.parse(it))
            })
        }
    }
}