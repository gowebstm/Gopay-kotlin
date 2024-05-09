package gowebs.`in`.gopay_sdk.api

import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class ApiResponseCallAdapterFactory : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (getRawType(returnType) != ApiResponse::class.java) {
            return null
        }
        if (returnType !is ParameterizedType) {
            throw IllegalArgumentException("ApiResponse return type must be parameterized")
        }
        val responseType = getParameterUpperBound(0, returnType)
        return ApiResponseCallAdapter<Any>(responseType)
    }

    private class ApiResponseCallAdapter<T>(
        private val responseType: Type
    ) : CallAdapter<T, ApiResponse<T>> {
        override fun responseType(): Type = responseType

        override fun adapt(call: retrofit2.Call<T>): ApiResponse<T> {
            return try {
                val response = call.execute()
                if (response.isSuccessful) {
                    ApiResponse.Success(response.body()!!)
                } else {
                    ApiResponse.Error(response.code(), response.message())
                }
            } catch (e: Exception) {
                ApiResponse.Error(0, e.message ?: "Unknown error")
            }
        }
    }
}