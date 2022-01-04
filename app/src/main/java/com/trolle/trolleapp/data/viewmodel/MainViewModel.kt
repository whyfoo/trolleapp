package com.trolle.trolleapp.data.viewmodel

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.trolle.trolleapp.data.Cart
import com.trolle.trolleapp.data.CartResponse
import com.trolle.trolleapp.data.Item
import com.trolle.trolleapp.data.ItemResponse
import com.trolle.trolleapp.data.network.api.RetrofitClient
import com.trolle.trolleapp.data.sharedpref.SharedPreference
import com.trolle.trolleapp.ui.signin.SignInActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainViewModel: ViewModel() {


    val listItems = MutableLiveData<ArrayList<Cart>>()

    fun setSearchItems(ctx: Context) = GlobalScope.async{
        val sharedPreference: SharedPreference = SharedPreference(ctx)
        var status = sharedPreference.getValueBoolien("status", false)
        while (status) {
            status = sharedPreference.getValueBoolien("status", false)
            val query = sharedPreference.getValueInt("id_order")
            Log.d("homeit", "ngeloop" + query.toString())
            RetrofitClient.apiInstance
                    .getCartItems(query)
                    .enqueue(object: Callback<CartResponse>{
                        override fun onResponse(call: Call<CartResponse>, response: Response<CartResponse>) {
                            if (response.isSuccessful){
                                Log.d("homeit", response.body()?.data?.result.toString())
                                if(response.body()?.data?.result.toString() == "") {
                                    sharedPreference.save("inCart", false)
                                } else {
                                    listItems.value = response.body()?.data?.result
                                }
                            }
                        }

                        override fun onFailure(call: Call<CartResponse>, t: Throwable) {
                            Log.d("Failure", "ada error mainviewmodel")
                        }

                    })
            delay(5000)
        }
    }

    fun getSearchItems(): LiveData<ArrayList<Cart>>{
        return listItems
    }
}