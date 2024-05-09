package gowebs.`in`.gopay_sdk

import android.webkit.CookieManager
import gowebs.`in`.gopay_sdk.utils.Constants

object GopayCookieManager {
    fun saveCredentials(authKey: String?, authToken: String?, packageName: String?) {
        // Get the instance of CookieManager
        val cookieManager: CookieManager = CookieManager.getInstance()

        // Set cookies for authKey, authToken, and packageName
        cookieManager.setCookie(Constants.KEY_AUTH_KEY, authKey)
        cookieManager.setCookie(Constants.KEY_AUTH_TOKEN, authToken)
        cookieManager.setCookie(Constants.KEY_AUTH_PACKAGE_NAME, packageName)
    }

    fun getCookie(cookieName: String?): String {
        val cookieManager: CookieManager = CookieManager.getInstance()
        return cookieManager.getCookie(cookieName)
    }

    fun clearCookies() {
        val cookieManager: CookieManager = CookieManager.getInstance()
        cookieManager.removeAllCookies(null)
    }
}