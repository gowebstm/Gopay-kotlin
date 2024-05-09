package gowebs.`in`.gopay.api

import gowebs.`in`.gopay.model.VerifyModel
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST(UrlHelper.verify)
    fun verify(@FieldMap map: MutableMap<String, String>): ApiResponse<VerifyModel>

}
