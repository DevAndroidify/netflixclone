package com.example.netflixclone

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.netflixclone.databinding.FragmentRegisterBinding

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore


class register : Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding:FragmentRegisterBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentRegisterBinding.inflate(layoutInflater)
        binding.backpress.setOnClickListener {
            findNavController().popBackStack()
        }
        auth=FirebaseAuth.getInstance()
        binding.registera.setOnClickListener {

            val email=binding.registeremail.text.toString()
            val password=binding.registerpassword.text.toString()
            findNavController().navigate(R.id.action_register_to_main)
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