package com.trolle.trolleapp.data

data class OrderResponse (
        val status: String,
        val message: String,
        val data: OrderData
)

data class OrderData(
        val id_order: Int,
)