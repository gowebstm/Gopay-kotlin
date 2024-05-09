package gowebs.`in`.gopay_sdk.api

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import gowebs.`in`.gopay_sdk.api.UrlHelper.BASE_URL
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit

object RetrofitHelper {

    fun getInstance(context: Context): Retrofit {

        val httpClient = OkHttpClient.Builder()
            .cache(Cache(context.applicationContext.cacheDir, 100 * 1024 * 1024))
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)

        httpClient.addInterceptor { chain ->
            var builders = chain.request()
            try {
                builders = chain.request().newBuilder()
                    .header("Accept", "application/json")
                    .header("Accept", "application/x-www-form-urlencoded")
                    .build()

            } catch (e: IOException) {
                if (e is SocketTimeoutException || e is SocketException) {
                    Log.e("RetrofitHelper", "Socket Timeout or Socket Exception", e)
                } else {
                    Log.e("RetrofitHelper", "IOException", e)
                }
            } catch (e: Exception) {
                Log.e("RetrofitHelper", "Exception", e)
            }
            chain.proceed(builders)
        }

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(ApiResponseCallAdapterFactory())
            .build()
    }
}