package com.trolle.trolleapp.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.trolle.trolleapp.data.Item
import com.trolle.trolleapp.databinding.ItemRowListBinding
import java.text.NumberFormat
import java.util.*

class ItemAdapter: RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    private val list = ArrayList<Item>()

    fun setList(items: ArrayList<Item>){
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }

    inner class ItemViewHolder(val binding: ItemRowListBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: Item){
            val price = item.id/100
            binding.tvItemName.text = item.login
            binding.tvItemCount.text = "x${price%10+1}"

            val localeID = Locale("in", "ID")
            val rupiah = NumberFormat.getCurrencyInstance(localeID)

            binding.tvItemPrice.text = rupiah.format(price)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = ItemRowListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(view)

    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size


}