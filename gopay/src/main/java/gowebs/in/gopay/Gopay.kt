package gowebs.`in`.gopay

import android.content.Context

class Gopay {

    fun accessGopay(context: Context?): AccessGopay {
        return AccessGopay.getInstance(context)
    }

}