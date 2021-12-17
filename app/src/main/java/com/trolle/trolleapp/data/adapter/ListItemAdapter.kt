package com.trolle.trolleapp.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.trolle.trolleapp.R
import com.trolle.trolleapp.data.Item
import java.util.*

class ListItemAdapter(private val listItem: ArrayList<Item>) : RecyclerView.Adapter<ListItemAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_row_list, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, count, price) = listItem[position]
        holder.tvName.text = name
        holder.tvCount.text = count.toString()
        holder.tvPrice.text = price.toString()
    }

    override fun getItemCount(): Int {
        return listItem.size
    }


    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        var tvCount: TextView = itemView.findViewById(R.id.tv_item_count)
        var tvPrice: TextView = itemView.findViewById(R.id.tv_item_price)
    }
}