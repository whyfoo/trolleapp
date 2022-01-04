package com.trolle.trolleapp

import android.app.AlertDialog
import android.content.*
import android.os.Bundle
import android.provider.MediaStore
import android.text.InputType
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.trolle.trolleapp.data.*
import com.trolle.trolleapp.data.network.api.RetrofitClient
import com.trolle.trolleapp.data.sharedpref.SharedPreference
import com.trolle.trolleapp.data.viewmodel.MainViewModel
import com.trolle.trolleapp.databinding.FragmentHomeBinding
import com.trolle.trolleapp.ui.home.HomeActivity
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

        val sharedPreference: SharedPreference = SharedPreference(requireContext())
        val idUser = sharedPreference.getValueInt("id_user")

        if (sharedPreference.getValueBoolien("status", false)){
            Navigation.findNavController(view).navigate(R.id.payFragment)
        }

        binding.imageViewBarcode.setOnClickListener{
            val takePhotoIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(takePhotoIntent, 1)
        }

        binding.imageViewInput.setOnClickListener {

            val input = EditText(context)
            input.setHint("Enter ID")
            input.inputType = InputType.TYPE_CLASS_NUMBER

            val alertDialog = AlertDialog.Builder(requireContext())
            alertDialog.setTitle("Input Trolley ID")
            alertDialog.setView(input)
            alertDialog.setPositiveButton("Connect") { dialog, which ->
//                var input = input.text.toString()
                var input = Integer.parseInt(input.text.toString())

                connectRaspi(idUser, input)

                val alertDialogStatus = AlertDialog.Builder(requireContext())
                alertDialogStatus.apply {
                    setTitle("Success!")
                    setMessage("Your smartphone has successfully connected with Trolley ID = " + input.toString() +
                            "\nHappy Shopping!")
                    setPositiveButton("OK") { dialog, which ->
                        getOrderId(idUser)
                        sharedPreference.save("status", true)
                        Navigation.findNavController(view).navigate(R.id.payFragment)
                    }
                    alertDialogStatus.setNegativeButton("Cancel") { dialog, which ->
                        dialog.cancel()
                    }
                }
                alertDialogStatus.create().show()
            }
            alertDialog.setNegativeButton("Cancel") { dialog, which ->
                dialog.cancel()
            }.create().show()
        }
    }

    private fun connectRaspi(id_user: Int, id_raspi: Int){
        val retIn = RetrofitClient.apiInstance
        val idInfo = UserRaspi(id_user, id_raspi)
        retIn.connectRaspi(idInfo).enqueue(object : Callback<UserRaspiResponse> {
            override fun onFailure(call: Call<UserRaspiResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "Timeout", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<UserRaspiResponse>, response: Response<UserRaspiResponse>) {
                if (response.code() == 200) {
                    Toast.makeText(requireContext(), response.body()!!.message, Toast.LENGTH_LONG).show()

                } else {
                    var responseMessage = response.message()
                    response.errorBody()?.string()?.let {
                        val jsonObject = JSONObject(it)
                        val msg = jsonObject.getString("message")
                        responseMessage = "$msg"
                    }
                    val errorMessage = "Error: $responseMessage"
                    Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun getOrderId(id_user: Int){
        val retIn = RetrofitClient.apiInstance
        retIn.getOrderId(id_user).enqueue(object : Callback<OrderResponse> {
            override fun onFailure(call: Call<OrderResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "Timeout", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<OrderResponse>, response: Response<OrderResponse>) {
                if (response.code() == 200) {

                    response.body().let {
                        val idOrder: Int = it!!.data[it!!.data.size-1].id_order

                        val sharedPreference: SharedPreference = SharedPreference(requireContext())

                        sharedPreference.save("id_order", idOrder)

                        Toast.makeText(requireContext(), "Order ID set: " + sharedPreference.getValueInt("id_order"), Toast.LENGTH_LONG).show()

                    }

                } else {
                    var responseMessage = response.message()
                    val errorMessage = "Error: $responseMessage"
                    Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}