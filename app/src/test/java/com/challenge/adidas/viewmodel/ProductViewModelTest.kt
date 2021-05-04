package com.challenge.adidas.viewmodel

import com.challenge.adidas.common.CoroutineTestRule
import com.challenge.adidas.datastore.ProductDataStore
import com.challenge.adidas.fakeProducts
import com.challenge.adidas.presentation.ProductViewModel
import com.challenge.adidas.repository.ProductRepository
import com.challenge.adidas.usecase.ProductUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.*

class ProductViewModelTest {
    @RelaxedMockK
    lateinit var productRepository: ProductRepository

    @RelaxedMockK
    lateinit var productUseClass: ProductUseCase

    @RelaxedMockK
    lateinit var productDataStore: ProductDataStore


    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    private fun createViewModel() = ProductViewModel(productUseClass,coroutineTestRule.getDispatcher())

    @Test
    fun `when data is fetched successfully, then state is updated with it`() =
        coroutineTestRule.runBlockingTest {
                launch(Dispatchers.Main) {
                    coEvery {
                        productUseClass.getProducts()
                    }.coAnswers {
                        delay(200)
                        fakeProducts
                    }
                    val viewModel = createViewModel()

                    Assert.assertEquals(fakeProducts, viewModel.productLiveData.value?.data)

                }
            }
}