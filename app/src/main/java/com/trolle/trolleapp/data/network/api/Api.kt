package com.trolle.trolleapp.data.network.api

import com.trolle.trolleapp.data.*
import com.trolle.trolleapp.data.sharedpref.SharedPreference
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
    fun login(@Body info: SignInBody): Call<SignInResponse>

    @POST("users")
    @Headers("Content-Type:application/json")
    fun registerUser(
            @Body info: User
    ): Call<ResponseBody>

    @POST("raspiSession/requestSession")
    @Headers("Authorization: Bearer " + "eyJhbGciOiJIUzI1NiJ9.aGFuaXA.TgV2hhHJFndbvucYSV_qN_fzBY5nGJRBOv7NcX2FdIw")
    fun connectRaspi(
            @Body info: UserRaspi,
    ): Call<UserRaspiResponse>

    @GET("orders/getAllOrders/5")
    @Headers("Authorization: Bearer " + "eyJhbGciOiJIUzI1NiJ9.aGFuaXA.TgV2hhHJFndbvucYSV_qN_fzBY5nGJRBOv7NcX2FdIw")
    fun getOrderId(

    ): Call<OrderResponse>

    @POST("raspiSession/endSession")
    @Headers("Authorization: Bearer " + "eyJhbGciOiJIUzI1NiJ9.aGFuaXA.TgV2hhHJFndbvucYSV_qN_fzBY5nGJRBOv7NcX2FdIw")
    fun disconnectRaspi(
            @Body info: DisconnectRaspi,
    ): Call<UserRaspiResponse>

    @GET("ordersxproducts/getCart/{id_order}")
    @Headers("Authorization: Bearer " + "eyJhbGciOiJIUzI1NiJ9.aGFuaXA.TgV2hhHJFndbvucYSV_qN_fzBY5nGJRBOv7NcX2FdIw")
    fun getCartItems(
            @Path("id_order") id_order: Int
    ): Call<CartResponse>
}