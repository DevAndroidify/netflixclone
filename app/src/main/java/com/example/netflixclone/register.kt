package com.example.netflixclone

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.Settings.Global
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.netflixclone.api.erroruserresponse
import com.example.netflixclone.api.objectforapi
import com.example.netflixclone.api.userrequest
import com.example.netflixclone.api.userresponse
import com.example.netflixclone.databinding.FragmentRegisterBinding

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class register : Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding:FragmentRegisterBinding
    private lateinit var response:userresponse
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentRegisterBinding.inflate(layoutInflater)
        sharedPreferences = requireContext().getSharedPreferences("netflixuser", Context.MODE_PRIVATE)
        binding.backpress.setOnClickListener {
            findNavController().popBackStack()
        }
        auth=FirebaseAuth.getInstance()
        binding.registersubmit.setOnClickListener {

            val email=binding.registeremail.editText!!.text.toString()
            val password=binding.registerpassword.editText!!.text.toString()
            val apiservice=objectforapi.apiService
            GlobalScope.launch {
                try {
                  response=apiservice.registeruser(userrequest(email,password))
                 val token=response.token

                    withContext(Dispatchers.Main){
                            sharedPreferences.edit().putString("token",token).apply()
                        findNavController().navigate(R.id.action_register_to_main)

                    }

                }catch (e:Exception){
                   withContext(Dispatchers.Main){
                       Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
                    }

                }
            }


//            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
//                if (it.isSuccessful){
//                    val user=auth.currentUser
//                    Toast.makeText(requireContext(), "sdfsdf", Toast.LENGTH_SHORT).show()
//                   updateuser(user!!)
//
//                }else{
//                    Toast.makeText(requireContext(), "dfsdfsd", Toast.LENGTH_SHORT).show()
//                }
//            }
        }

        return binding.root
    }

    private fun updateuser(user: FirebaseUser) {
        val db = Firebase.firestore
          val datauser= hashMapOf(
              "email" to user.email

          )

        db.collection("users").document(user.uid)
            .set(datauser)
            .addOnSuccessListener {
                Toast.makeText(requireContext(), "User data stored successfully", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(requireContext(), "Failed to store user data: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }



}