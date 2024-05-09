package gowebs.`in`.gopay_sdk

import android.content.Context
import android.util.Log
import gowebs.`in`.gopay_sdk.api.ApiResponse
import gowebs.`in`.gopay_sdk.api.ApiService
import gowebs.`in`.gopay_sdk.api.RetrofitHelper
import gowebs.`in`.gopay_sdk.model.VerifyModel
import gowebs.`in`.gopay_sdk.utils.Constants
import gowebs.`in`.gopay_sdk.utils.NetworkUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class AccessGopay private constructor(context: Context?) {

    private var accessGopayContext: Context? = context?.applicationContext
    private var gopayToken: String? = null
    private var gopayKey: String? = null
    private var gopayDomain: String? = null
    private var gopayPackageName: String? = null

    companion object {

        private var accessGopay: AccessGopay? = null

        fun getInstance(context: Context?): AccessGopay {
            if (accessGopay == null) {
                accessGopay = AccessGopay(context)
            }
            return accessGopay!!
        }
    }

    fun authToken(token: String?): AccessGopay {
        token?.let { token ->
            gopayToken = token
        }
        return this
    }

    fun authKey(key: String?): AccessGopay {
        key?.let { key ->
            gopayKey = key
        }
        return this
    }

    fun authDomain(domain: String?): AccessGopay {
        domain?.let { domain ->
            gopayDomain = domain
        }
        return this
    }

    fun authPackage(packageName: String?): AccessGopay {
        packageName?.let { packageName ->
            gopayPackageName = packageName
        }
        return this
    }

    suspend fun verify(): VerifyModel? {
        var data: VerifyModel? = null
        if (gopayKey != null && gopayToken != null && gopayPackageName != null) {
            val hashMap = HashMap<String, String>()
            hashMap[Constants.KEY_AUTH_KEY] = gopayKey!!
            hashMap[Constants.KEY_AUTH_TOKEN] = gopayToken!!
            hashMap[Constants.KEY_AUTH_PACKAGE_NAME] = gopayPackageName!!
            val apiService = RetrofitHelper.getInstance(accessGopayContext!!).create(ApiService::class.java)
            if (NetworkUtils.isNetworkAvailable(accessGopayContext)) {
                try {
                    val result = withContext(Dispatchers.IO) {
                        apiService.verify(hashMap)
                    }
                    when (result) {
                        is ApiResponse.Success -> {
                            data = result.data
                            data.let { res ->
                                val response = JSONObject().apply {
                                    put("code", res.code)
                                    put("appName", res.appName)
                                    put("authKey", res.authKey)
                                    put("authToken", res.authToken)
                                    put("packageName", res.packageName)
                                    put("status", res.status)
                                }.toString()
                                Log.i("Gopay Verification", "Success: $response")
                            } ?: run {
                                Log.i("Gopay Verification", "Success: $data")
                            }
                        }
                        is ApiResponse.Error -> {
                            val errorCode = result.code
                            val errorMessage = result.message
                            data = result.data
                            Log.e("Gopay Verification", "Error: $errorCode - $errorMessage")
                        }
                    }
                } catch (e: Exception) {
                    Log.e("Gopay Verification", "Exception: ${e.message}", e)
                }
            }
        } else {
            throw IllegalStateException("Parameters are not set properly. Check again.")
        }
        return data
    }
}