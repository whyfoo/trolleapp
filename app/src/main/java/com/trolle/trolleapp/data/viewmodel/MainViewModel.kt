package com.trolle.trolleapp.data.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.trolle.trolleapp.data.Item
import com.trolle.trolleapp.data.ItemResponse
import com.trolle.trolleapp.data.network.api.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainViewModel: ViewModel() {

    val listItems = MutableLiveData<ArrayList<Item>>()

    fun setSearchItems(query: String){
        RetrofitClient.apiInstance
                .getSearchItems(query)
                .enqueue(object: Callback<ItemResponse>{
                    override fun onResponse(call: Call<ItemResponse>, response: Response<ItemResponse>) {
                        if (response.isSuccessful){
                            listItems.postValue(response.body()?.items)
                        }
                    }

                    override fun onFailure(call: Call<ItemResponse>, t: Throwable) {
                        Log.d("Failure", "ada error mainviewmodel")
                    }

                })
    }

    fun getSearchItems(): LiveData<ArrayList<Item>>{
        return listItems
    }
}