package com.example.phonepe.common

sealed class Resource<T>(val isLoading: Boolean, data: T? = null, var error: String? = null) {
    class Success<T>(val data: T) : Resource<T>(false, data)
    class Error<T>(error: String?) : Resource<T>(isLoading = false, error = error)
    class Loading<T> : Resource<T>(isLoading = true)
}
