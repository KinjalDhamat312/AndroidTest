package com.demo.androiddemo.data.remote.result

import androidx.annotation.Keep


@Keep
sealed class Resource<out T> {
    class Success<out T>(val data: T) : Resource<T>()
    class Error<out T>(val error: BaseError) : Resource<T>()
    class Loading<out T>(val loading: Boolean) : Resource<T>()
}
