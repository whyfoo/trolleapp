package com.trolle.trolleapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.trolle.trolleapp.data.Item
import com.trolle.trolleapp.data.adapter.ListItemAdapter
import com.trolle.trolleapp.databinding.FragmentPayBinding
import com.trolle.trolleapp.ui.pay.CheckoutActivity
import com.trolle.trolleapp.ui.side_menu.EditProfileActivity
import com.trolle.trolleapp.ui.signin.SignInActivity
import java.util.*

class PayFragment : Fragment() {

    private lateinit var rvItems: RecyclerView
    private val list = ArrayList<Item>()
    private var _binding: FragmentPayBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPayBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvItems = binding.rvItems
        rvItems.setHasFixedSize(true)

        Handler(Looper.getMainLooper()).postDelayed({
            showLoading(true)
            Handler(Looper.getMainLooper()).postDelayed({
                showLoading(false)
            }, 2000)
        }, 2000)

        list.addAll(listItems)
        showRecyclerList()

        binding.buttonCheckout.setOnClickListener {
            requireActivity().run{
                startActivity(Intent(context, CheckoutActivity::class.java))
            }
        }
    }

    private val listItems: ArrayList<Item>
        get() {
            val dataName = resources.getStringArray(R.array.data_name)
            val dataCount = resources.getIntArray(R.array.data_count)
            val dataPrice = resources.getIntArray(R.array.data_price)
            val listItem = ArrayList<Item>()

            for (i in 1 until (dataName.size-1)) {
                val item = Item(dataName.get(i),dataCount.get(i), dataPrice.get(i))
                listItem.add(item)
            }
            return listItem
        }

    private fun showRecyclerList() {
        rvItems.layoutManager = LinearLayoutManager(context)
        val listItemAdapter = ListItemAdapter(list)
        rvItems.adapter = listItemAdapter
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
            binding.tvItemEmpty.visibility = View.GONE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.rvItems.visibility = View.VISIBLE
        }
    }

}