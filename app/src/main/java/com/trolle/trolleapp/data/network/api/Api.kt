package com.trolle.trolleapp.data.network.api

import com.trolle.trolleapp.data.ItemResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface Api {
    @GET("search/users")
    @Headers("Authorization: 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",  "User-Agent: request")
    fun getSearchItems(
            @Query("q") query: String?
    ): Call<ItemResponse>
}