package gowebs.`in`.gopay_sdk

import android.R
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import gowebs.`in`.gopay_sdk.AccessGopay.Companion.isVerified
import gowebs.`in`.gopay_sdk.api.ApiService
import gowebs.`in`.gopay_sdk.api.RetrofitHelper
import gowebs.`in`.gopay_sdk.api.UrlHelper
import gowebs.`in`.gopay_sdk.utils.Constants
import gowebs.`in`.gopay_sdk.utils.NetworkUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.random.Random

class CheckOut private constructor(context: Context?) {

    private var CheckOutContext: Context? = context?.applicationContext
    private var CheckOutTxnID: String? = null

    companion object {

        private var checkOutInit: CheckOut? = null

        fun getInstance(context: Context?): CheckOut {
            if (checkOutInit == null) {
                checkOutInit = CheckOut(context)
            }
            return checkOutInit!!
        }
    }

    suspend fun createOrder(
        name: String?,
        phone: String?,
        email: String?,
        domain: String?,
        amount: Int?,
        description: String?,
    ) {
        if (amount != null && name != null && phone != null && domain != null && validateMobileNumber(
                phone
            ) && isVerified
        ) {
            val transactionID = generateTxnId()
            CheckOutTxnID = transactionID
            val apiService =
                RetrofitHelper.getInstance(CheckOutContext!!).create(ApiService::class.java)
            if (NetworkUtils.isNetworkAvailable(CheckOutContext)) {
                try {
                    val result = withContext(Dispatchers.IO) {
                        apiService.createOrder(
                            appKey = GopayCookieManager.getCookie(Constants.KEY_AUTH_KEY),
                            token = GopayCookieManager.getCookie(Constants.KEY_AUTH_TOKEN),
                            name = name,
                            email = email ?: "",
                            phone = phone,
                            domain = domain,
                            amount = amount.toString(),
                            description = description ?: "",
                            txnId = transactionID,
                            returnUrl = "${UrlHelper.BASE_URL}${UrlHelper.success}"
                        )
                    }
                    result.body()?.let {
                        if (it.status) {
                            openCustomTab(CheckOutContext!!, it.url)
                        }
                    }
                } catch (e: Exception) {
                    Log.e("PAYMENT", "Exception: ${e.message}", e)
                }
            }
        } else {
            throw IllegalStateException("Parameters are not set properly. Check again.")
        }
    }

    private fun generateTxnId(): String {
        val transID = (1..9).map { Random.nextInt(0, 10) }.joinToString("")
        return "TW$transID"
    }

    private fun validateMobileNumber(number: String): Boolean {
        val mobilePattern = Regex("^[6-9]\\d{9}$")
        return mobilePattern.matches(number)
    }

    private fun openCustomTab(context: Context, url: String?) {
        val builder = CustomTabsIntent.Builder()
        builder.setToolbarColor(ContextCompat.getColor(context, R.color.white))
        val customTabsIntent = builder.build()

        val packageName = "com.android.chrome"
        if (packageName != null) {
            customTabsIntent.intent.setPackage(packageName)
            customTabsIntent.intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(customTabsIntent.intent.setData(Uri.parse(url)))
        } else {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }
}