package gowebs.`in`.gopay_sdk

import android.content.Context

class Gopay {

    fun accessGopay(context: Context?): AccessGopay {
        return AccessGopay.getInstance(context)
    }

}