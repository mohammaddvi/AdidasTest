package com.challenge.adidas.common

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

open class CoroutineDispatcherProvider {
    open fun ioDispatcher() = Dispatchers.IO
    open fun bgDispatcher() = Dispatchers.Default
    open fun uiDispatcher(): CoroutineDispatcher = Dispatchers.Main


    fun CoroutineDispatcher.asDispatcherProvider(): CoroutineDispatcherProvider {
        return createTestCoroutineDispatcherProvider(this)
    }

    private fun createTestCoroutineDispatcherProvider(coroutineDispatcher: CoroutineDispatcher): CoroutineDispatcherProvider {
        return object : CoroutineDispatcherProvider() {
            override fun bgDispatcher(): CoroutineDispatcher = coroutineDispatcher
            override fun ioDispatcher(): CoroutineDispatcher = coroutineDispatcher
            override fun uiDispatcher(): CoroutineDispatcher = coroutineDispatcher
        }
    }
}