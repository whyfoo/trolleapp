package com.trolle.trolleapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.trolle.trolleapp.data.Item
import com.trolle.trolleapp.data.adapter.ItemAdapter
import com.trolle.trolleapp.data.sharedpref.SharedPreference
import com.trolle.trolleapp.data.viewmodel.MainViewModel
import com.trolle.trolleapp.databinding.FragmentPayBinding
import com.trolle.trolleapp.ui.pay.CheckoutActivity
import java.util.*

class PayFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: ItemAdapter

    private lateinit var rvItems: RecyclerView
    private val list = ArrayList<Item>()
    private var _binding: FragmentPayBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPayBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var subTotal: Int = 0
        binding.textViewSubTotalPrice.text = subTotal.toString()

        adapter = ItemAdapter()
        adapter.notifyDataSetChanged()
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)

        binding.rvItems.layoutManager = LinearLayoutManager(context)
        binding.rvItems.setHasFixedSize(true)
        binding.rvItems.adapter = adapter

        searchItem()

        Toast.makeText(context, "Shopping process started", Toast.LENGTH_SHORT).show()

        viewModel.getSearchItems().observe(viewLifecycleOwner, {
            if (it != null) {
                showLoading(true)
                var totalPrice = 0
                for (i in 0..it.size - 1) {
                    totalPrice += it.get(i).price_per_unit * it.get(i).quantity
                }
                adapter.setList(it)
                showLoading(false)
                binding.textViewSubTotalPrice.text = getString(R.string.sub_total_price_dummy, totalPrice)

                subTotal = totalPrice
            }
        })

        binding.buttonCheckout.setOnClickListener {
            requireActivity().run{
                val intent = Intent(requireActivity().applicationContext, CheckoutActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.putExtra("price", subTotal)
                requireActivity().applicationContext.startActivity(intent)

                //startActivity(Intent(context, CheckoutActivity::class.java))
            }
        }
    }

    private fun searchItem(){
        viewModel.setSearchItems(requireContext())

    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
            binding.tvItemEmpty.visibility = View.GONE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.rvItems.visibility = View.VISIBLE
            binding.layoutSubtotal.visibility = View.VISIBLE
        }
    }

}