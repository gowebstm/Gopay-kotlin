package gowebs.`in`.gopay_sdk.api

import gowebs.`in`.gopay_sdk.model.CreateOrderModel
import gowebs.`in`.gopay_sdk.model.VerifyModel
import retrofit2.Response
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @FormUrlEncoded
    @POST(UrlHelper.verify)
    fun verify(@FieldMap map: MutableMap<String, String>): ApiResponse<VerifyModel>

    @GET(UrlHelper.checkout)
    suspend fun createOrder(
        @Query("app_key") appKey: String,
        @Query("token") token: String,
        @Query("name") name: String,
        @Query("email") email: String,
        @Query("phone") phone: String,
        @Query("domain") domain: String,
        @Query("amount") amount: String,
        @Query("description") description: String,
        @Query("txnid") txnId: String,
        @Query("return_url") returnUrl: String,
    ): Response<CreateOrderModel>

}
