package gowebs.`in`.gopay

import android.content.Context
import android.util.Log
import gowebs.`in`.gopay.api.ApiResponse
import gowebs.`in`.gopay.api.ApiService
import gowebs.`in`.gopay.api.RetrofitHelper
import gowebs.`in`.gopay.utils.Constants
import gowebs.`in`.gopay.utils.NetworkUtils
import gowebs.`in`.gopay.model.VerifyModel

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

    fun verify() {
        Log.e("Gopay Verification", "Hey")
    }

//    fun verify(): VerifyModel? {
//        Log.e("Gopay Verification", "Hey")
//        var data: VerifyModel? = null
//        if (gopayKey != null && gopayToken != null && gopayPackageName != null) {
//            val hashMap = HashMap<String, String>()
//            hashMap[Constants.KEY_AUTH_KEY] = gopayKey!!
//            hashMap[Constants.KEY_AUTH_TOKEN] = gopayToken!!
//            hashMap[Constants.KEY_AUTH_PACKAGE_NAME] = gopayPackageName!!
//            val apiService =
//                RetrofitHelper.getInstance(accessGopayContext!!).create(ApiService::class.java)
//            if (NetworkUtils.isNetworkAvailable(accessGopayContext)) {
//                when (val result = apiService.verify(hashMap)) {
//
//                    is ApiResponse.Success -> {
//                        data = result.data
//                        Log.e("Gopay Verification", "Error: ${data.code} - $data")
//                    }
//
//                    is ApiResponse.Error -> {
//                        val errorCode = result.code
//                        val errorMessage = result.message
//                        data = result.data
//                        Log.e("Gopay Verification", "Error: $errorCode - $errorMessage")
//                    }
//
//                }
//            }
//        } else {
//            throw IllegalStateException("Parameters are not set properly. Check again.")
//        }
//        return data
//    }
}