package gowebs.`in`.gopay.api

sealed class ApiResponse<out T> {
    data class Success<T>(val data: T) : ApiResponse<T>()
    data class Error<T>(val code: Int, val message: String, val data: T? = null) : ApiResponse<T>()
}