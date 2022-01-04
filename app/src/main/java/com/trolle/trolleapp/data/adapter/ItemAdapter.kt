package com.trolle.trolleapp.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.trolle.trolleapp.data.Item
import com.trolle.trolleapp.databinding.ItemRowListBinding
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
            binding.tvItemName.text = item.login
            binding.tvItemCount.text = item.type
            binding.tvItemPrice.text = item.id.toString()

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