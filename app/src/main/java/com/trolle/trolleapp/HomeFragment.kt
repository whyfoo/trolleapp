package com.trolle.trolleapp

import android.app.AlertDialog
import android.content.*
import android.os.Bundle
import android.provider.MediaStore
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
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

            val takePhotoIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(takePhotoIntent, 1)


//            val alertDialog = AlertDialog.Builder(requireContext())
//            alertDialog.apply {
//                setTitle("Success!")
//                setMessage("Your smartphone has successfully connected with Trolley ID = (ID). " +
//                        "     Happy Shopping!")
//                setPositiveButton("OK") { dialog, which ->
//                    Navigation.findNavController(view).navigate(R.id.payFragment)
//                }
//                alertDialog.setNegativeButton("Cancel") { dialog, which ->
//                    dialog.cancel()
//                }
//            }
//            alertDialog.create().show()
        }

        binding.imageViewInput.setOnClickListener {

            val input = EditText(context)
            input.setHint("Enter ID")
            input.inputType = InputType.TYPE_CLASS_NUMBER


            val alertDialog = AlertDialog.Builder(requireContext())
            alertDialog.setTitle("Input Trolley ID")
            alertDialog.setView(input)
            alertDialog.setPositiveButton("Connect") { dialog, which ->
                var input = input.text.toString()

                val alertDialogStatus = AlertDialog.Builder(requireContext())
                alertDialogStatus.apply {
                    setTitle("Success!")
                    setMessage("Your smartphone has successfully connected with Trolley ID = " + input +
                            "     Happy Shopping!")
                    setPositiveButton("OK") { dialog, which ->
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
}