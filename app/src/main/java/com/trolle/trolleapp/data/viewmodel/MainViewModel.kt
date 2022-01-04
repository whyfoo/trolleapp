package com.trolle.trolleapp.data.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.trolle.trolleapp.data.Cart
import com.trolle.trolleapp.data.CartResponse
import com.trolle.trolleapp.data.Item
import com.trolle.trolleapp.data.ItemResponse
import com.trolle.trolleapp.data.network.api.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainViewModel: ViewModel() {

    val listItems = MutableLiveData<ArrayList<Cart>>()

    fun setSearchItems(query: Int){
        RetrofitClient.apiInstance
                .getCartItems(query)
                .enqueue(object: Callback<CartResponse>{
                    override fun onResponse(call: Call<CartResponse>, response: Response<CartResponse>) {
                        if (response.isSuccessful){
                            listItems.postValue(response.body()?.data?.result)
                        }
                    }

                    override fun onFailure(call: Call<CartResponse>, t: Throwable) {
                        Log.d("Failure", "ada error mainviewmodel")
                    }

                })
    }

    fun getSearchItems(): LiveData<ArrayList<Cart>>{
        return listItems
    }
}