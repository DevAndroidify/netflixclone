package com.example.netflixclone

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.netflixclone.api.objectforapi
import com.example.netflixclone.api.userrequest
import com.example.netflixclone.databinding.FragmentSigninBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class signin : Fragment() {

   private lateinit var binding:FragmentSigninBinding
   private lateinit var sharedPreferences: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding=FragmentSigninBinding.inflate(layoutInflater)
        sharedPreferences=requireContext().getSharedPreferences("netflixuser",Context.MODE_PRIVATE)
        binding.signinbackbutton.setOnClickListener {
            findNavController().popBackStack()
        }
        val apiservice=objectforapi.apiService
        binding.registersubmit.setOnClickListener {
            val email=binding.registeremail.editText!!.text.toString()
            val password=binding.registerpassword.editText!!.text.toString()
            GlobalScope.launch {
                try {
                    val response=apiservice.loginuser(userrequest(email, password))
                    withContext(Dispatchers.Main){
                         findNavController().navigate(R.id.action_signin_to_main)
                    }

                }catch (e:Exception){
                    withContext(Dispatchers.Main){
                        Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }
        binding.signinregister.setOnClickListener {
            findNavController().navigate(R.id.action_signin_to_register)
        }

        return binding.root
    }


}