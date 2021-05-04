package com.challenge.adidas.common

sealed class LoadableData<out T> {

    abstract val data: T?
}

data class Loaded<T>(override val data: T) : LoadableData<T>()
data class Failed<T>(val error: Throwable, val message:String) : LoadableData<T>() {
    override val data: T?
        get() = null
}

object Loading : LoadableData<Nothing>() {
    override val data: Nothing?
        get() = null
}

object NotLoaded : LoadableData<Nothing>() {
    override val data: Nothing?
        get() = null
}