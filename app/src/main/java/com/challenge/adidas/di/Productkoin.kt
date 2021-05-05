package com.challenge.adidas.di

import com.challenge.adidas.common.errorhandling.ErrorParser
import com.challenge.adidas.common.errorhandling.ErrorParserImpl
import com.challenge.adidas.common.network.AuthInterceptor
import com.challenge.adidas.common.network.provideOkHttpClient
import com.challenge.adidas.common.network.provideRetrofit
import com.challenge.adidas.datastore.InMemoryProductDataStore
import com.challenge.adidas.datastore.ProductDataStore
import com.challenge.adidas.network.ProductApi
import com.challenge.adidas.presentation.ProductViewModel
import com.challenge.adidas.repository.ProductRepository
import com.challenge.adidas.repository.RemoteProductRepository
import com.challenge.adidas.usecase.ProductUseCase
import com.challenge.adidas.usecase.ProductUseCaseImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.create

val productModule = module {
    factory<ProductRepository> {
        RemoteProductRepository(get())
    }
    factory<ProductUseCase> { ProductUseCaseImpl(get(), get()) }
    single { AuthInterceptor() }
    single<ProductDataStore> {
        InMemoryProductDataStore()
    }
    single<ProductApi> {
        provideRetrofit(get()).create()
    }
    factory<ErrorParser> { ErrorParserImpl() }
    single { provideOkHttpClient(get()) }

    viewModel {
        ProductViewModel(get(), get())
    }


}