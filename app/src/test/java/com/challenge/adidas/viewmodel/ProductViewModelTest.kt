package com.challenge.adidas.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.challenge.adidas.network.Product
import com.challenge.adidas.common.Failed
import com.challenge.adidas.common.Loading
import com.challenge.adidas.common.errorhandling.ErrorParser
import com.challenge.adidas.datastore.ProductDataStore
import com.challenge.adidas.fakeProducts
import com.challenge.adidas.fakeThrowable
import com.challenge.adidas.presentation.ProductViewModel
import com.challenge.adidas.repository.ProductRepository
import com.challenge.adidas.usecase.ProductUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.unmockkAll
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.rules.TestRule


class ProductViewModelTest {
    @RelaxedMockK
    lateinit var productRepository: ProductRepository

    @RelaxedMockK
    lateinit var productUseClass: ProductUseCase

    @RelaxedMockK
    lateinit var errorParser: ErrorParser

    @RelaxedMockK
    lateinit var productDataStore: ProductDataStore
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")


    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        unmockkAll()
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    private fun createViewModel() = ProductViewModel(productUseClass, errorParser)

    @Test
    fun `when data is fetched successfully, then state is updated with it`() {
        runBlocking {
            launch(Dispatchers.Main) {
                coEvery {
                    productUseClass.getProducts()
                }.coAnswers {
                    delay(100)
                    fakeProducts
                }
                val viewModel = createViewModel()
                delay(200)

                Assert.assertEquals(fakeProducts, viewModel.productLiveData.value?.data)

            }
        }
    }

    @Test
    fun `when data is in fetching, then state is updated with it`() {
        runBlocking {
            launch(Dispatchers.Main) {
                coEvery {
                    productUseClass.getProducts()
                }.coAnswers {
                    delay(200)
                    fakeProducts
                }

                val viewModel = createViewModel()

                Assert.assertEquals(Loading, viewModel.productLiveData.value)
            }
        }
    }

    @Test
    fun `when data is not fetched, then state is updated with it `(){
        runBlocking {
            launch(Dispatchers.Main) {
                coEvery {
                    productUseClass.getProducts()
                }.coAnswers {
                    delay(200)
                    throw fakeThrowable
                }

                val viewModel = createViewModel()
                delay(300)

                Assert.assertEquals(Failed<List<Product>>(fakeThrowable, errorParser.parse(
                    fakeThrowable)),
                    viewModel.productLiveData.value)
            }
        }
    }
}