package com.challenge.adidas.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.challenge.adidas.Product
import com.challenge.adidas.common.Failed
import com.challenge.adidas.common.Loading
import com.challenge.adidas.common.errorhandling.ErrorParser
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
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.rules.TestRule


class ProductViewModelTest {

    @RelaxedMockK
    lateinit var productUseClass: ProductUseCase


    @RelaxedMockK
    lateinit var productRepository: ProductRepository

    @RelaxedMockK
    lateinit var errorParser: ErrorParser

    private val testThread = newSingleThreadContext("UI thread")


    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testThread)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    private fun createViewModel() =
        ProductViewModel(productUseClass, productRepository,errorParser)

    @Test
    fun `when product data is fetched successfully, then state is updated with it`() {
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
    fun `when product data is in fetching, then state is updated with it`() {
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
    fun `when product data is not fetched, then state is updated with it `() {
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

                Assert.assertEquals(
                    Failed<List<Product>>(
                        fakeThrowable, errorParser.parse(
                            fakeThrowable
                        )
                    ),
                    viewModel.productLiveData.value
                )
            }
        }
    }

    @Test
    fun `when detials data is not fetched, then state is updated with it `() {
        runBlocking {
            launch(Dispatchers.Main) {
                coEvery {
                    productUseClass.getProductById(any())
                }.coAnswers {
                    delay(200)
                    throw fakeThrowable
                }

                val viewModel = createViewModel()
                viewModel.productDetailsRequested("1")
                delay(300)

                Assert.assertEquals(
                    Failed<Product>(
                        fakeThrowable, errorParser.parse(
                            fakeThrowable
                        )
                    ),
                    viewModel.detailsLiveData.value
                )
            }
        }
    }

    @Test
    fun `when details data is fetched successfully, then state is updated with it`() {
        runBlocking {
            launch(Dispatchers.Main) {
                coEvery {
                    productUseClass.getProductById(any())
                }.coAnswers {
                    delay(100)
                    fakeProducts[0]
                }
                val viewModel = createViewModel()
                viewModel.productDetailsRequested("1")
                delay(200)

                Assert.assertEquals(fakeProducts[0], viewModel.detailsLiveData.value?.data)

            }
        }
    }

    @Test
    fun `when details data is in fetching, then state is updated with it`() {
        runBlocking {
            launch(Dispatchers.Main) {
                coEvery {
                    productUseClass.getProductById(any())
                }.coAnswers {
                    delay(200)
                    fakeProducts[0]
                }

                val viewModel = createViewModel()
                viewModel.productDetailsRequested("1")

                Assert.assertEquals(Loading, viewModel.detailsLiveData.value)
            }
        }
    }
}