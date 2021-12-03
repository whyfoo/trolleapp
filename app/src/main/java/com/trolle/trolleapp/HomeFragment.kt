package com.trolle.trolleapp

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.trolle.trolleapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageViewBarcode.setOnClickListener{

            val alertDialog = AlertDialog.Builder(requireContext())
            alertDialog.apply {
                setTitle("Success!")
                setMessage("Your smartphone has successfully connected with Trolley ID = (ID). " +
                        "     Happy Shopping!")
                setPositiveButton("OK") { _, _ ->
                    //Toast.makeText(requireContext(),  "yes", Toast.LENGTH_SHORT).show()
                    Navigation.findNavController(view).navigate(R.id.payFragment)
                }.create().show()
            }
        }
    }
}