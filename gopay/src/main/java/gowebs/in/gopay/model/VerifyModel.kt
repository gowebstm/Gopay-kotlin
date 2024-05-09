package gowebs.`in`.gopay.model

data class VerifyModel(
    val appName: String,
    val authKey: String,
    val authToken: String,
    val code: Int,
    val packageName: String,
    val status: Int
)