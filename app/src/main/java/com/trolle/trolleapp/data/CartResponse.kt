package com.trolle.trolleapp.data

import java.util.*

data class CartResponse (
        val status: String,
        val message: String,
        val data: CartData
)

data class CartData(
        val result: ArrayList<Cart>,
        val total_price: Int
)

data class Cart(
        val name: String,
        val quantity: Int,
        val price_per_unit: Int,
)