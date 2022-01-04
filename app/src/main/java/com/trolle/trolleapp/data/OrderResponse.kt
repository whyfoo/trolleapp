package com.trolle.trolleapp.data

import java.util.*

data class OrderResponse (
        val status: String,
        val message: String,
        val data: OrderData
)

data class OrderData(
        val id_order: Int,
        val tanggal: Date,
        val status_transaksi: String
)