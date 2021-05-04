package com.challenge.adidas.viewmodel

import com.challenge.adidas.datastore.ProductDataStore
import com.challenge.adidas.repository.ProductRepository
import com.challenge.adidas.usecase.ProductUseCase
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Before

class ProductViewModelTest {
    @RelaxedMockK
    lateinit var productRepository: ProductRepository

    @RelaxedMockK
    lateinit var productUseClass: ProductUseCase

    @RelaxedMockK
    lateinit var productDataStore: ProductDataStore

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }
}