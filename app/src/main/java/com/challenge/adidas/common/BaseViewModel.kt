package com.challenge.adidas.common

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class BaseViewModel() : ViewModel() {
    private fun create() {
        onCreate()
    }

    protected open fun onCreate() {

    }

//    protected suspend fun <T> onIO(coroutine: suspend () -> T): suspend () -> T {
//        return withContext(coroutineDispatcherProvider.ioDispatcher) {
//            coroutine
//        }
//    }
//
//    protected suspend fun <T> onBg(coroutine: suspend () -> T): suspend () -> T {
//        return withContext(coroutineDispatcherProvider.bgDispatcher) {
//            coroutine
//        }
//    }
//
//    protected suspend fun <T> onUI(coroutine: suspend () -> T): suspend () -> T {
//        return withContext(coroutineDispatcherProvider.uiDispatcher) {
//            coroutine
//        }
//    }

}