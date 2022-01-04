package com.trolle.trolleapp.data.network.api

import com.trolle.trolleapp.data.ItemResponse
import com.trolle.trolleapp.data.SignInBody
import com.trolle.trolleapp.data.User
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface Api {
    @GET("search/users")
    @Headers("Authorization: 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",  "User-Agent: request")
    fun getSearchItems(
            @Query("q") query: String?
    ): Call<ItemResponse>

    @POST("users/login")
    @Headers("Content-Type:application/json")
    fun login(@Body info: SignInBody): Call<ResponseBody>

    @POST("users")
    @Headers("Content-Type:application/json")
    fun registerUser(
            @Body info: User
    ): Call<ResponseBody>
}