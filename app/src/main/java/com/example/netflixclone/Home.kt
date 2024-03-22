package com.example.netflixclone

import android.R
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.example.netflixclone.databinding.FragmentHomeBinding
import com.example.netflixclone.models.movies
import com.example.netflixclone.models.slidemodel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore


class Home : Fragment() {
          private  lateinit var stname:Array<String>
                 private  lateinit var stdesc:Array<String>
                 private lateinit  var stvideo:Array<String>
           private lateinit  var binding:FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentHomeBinding.inflate(layoutInflater)
        val list= arrayListOf<movies>()
        stname = arrayOf("", "", "") // Initialize with appropriate size and default values
        stdesc = arrayOf("", "", "") // Initialize with appropriate size and default values
        stvideo = arrayOf("", "", "") 

        val db=Firebase.firestore

        db.collection("videos").get().addOnSuccessListener {

             stname[0]=it.documents[13].getString("moviename")!!
             stdesc[0]=it.documents[13].getString("moviedescription")!!
             stvideo[0]=it.documents[13].getString("downloadUrl")!!
            stname[1]=it.documents[1].getString("moviename")!!
            stdesc[1]=it.documents[1].getString("moviedescription")!!
            stvideo[1]=it.documents[1].getString("downloadUrl")!!
            stname[2]=it.documents[14].getString("moviename")!!
            stdesc[2]=it.documents[14].getString("moviedescription")!!
            stvideo[2]=it.documents[14].getString("downloadUrl")!!
            for(documents in it.documents){
                val imageurl=documents.getString("imageurl")
                val downloadurl=documents.getString("downloadUrl")
                val moviename=documents.getString("moviename")
                val moviedescription=documents.getString("moviedescription")
                list.add(movies(moviename,moviedescription,imageurl,downloadurl))

            }
            binding.movierecylerview.adapter=movieadapter(requireContext(),list)
            val imageList = ArrayList<SlideModel>() // Create image list



            imageList.add(SlideModel("https://firebasestorage.googleapis.com/v0/b/netflixclone-9d67c.appspot.com/o/images%2Fimage_1710830602193.jpg?alt=media&token=ee393a03-4502-41e7-9954-8eae49849dea"))
            imageList.add(SlideModel("https://firebasestorage.googleapis.com/v0/b/netflixclone-9d67c.appspot.com/o/images%2Fimage_1710834511896.jpg?alt=media&token=2c425ecc-74fc-4fc5-844e-e5f293ec001e"))
            imageList.add(SlideModel("https://firebasestorage.googleapis.com/v0/b/netflixclone-9d67c.appspot.com/o/images%2Fimage_1710833699012.jpg?alt=media&token=13a0e033-b74c-4de6-8bf3-621cf66475c0"))

                binding.viewpager2.setImageList(imageList)
            binding.viewpager2.setItemClickListener(object : ItemClickListener {
                override fun onItemSelected(position: Int) {

                        if(position==0){
                            val a=Intent(requireContext(),movieactivity::class.java)
                            a.putExtra("movietitle",stname[0])
                            a.putExtra("moviedesc",stdesc[0])
                            a.putExtra("video",stvideo[0])
                            startActivity(a)
                        }
                    if(position==1){
                        val a=Intent(requireContext(),movieactivity::class.java)
                        a.putExtra("movietitle",stname[1])
                        a.putExtra("moviedesc",stdesc[1])
                        a.putExtra("video",stvideo[1])
                        startActivity(a)
                    }
                    if(position==2){
                        val a=Intent(requireContext(),movieactivity::class.java)
                        a.putExtra("movietitle",stname[2])
                        a.putExtra("moviedesc",stdesc[2])
                        a.putExtra("video",stvideo[2])
                        startActivity(a)
                    }



                }
                override fun doubleClick(position: Int) {
                    // Do not use onItemSelected if you are using a double click listener at the same time.
                    // Its just added for specific cases.
                    // Listen for clicks under 250 milliseconds.
                } })

        }






        binding.addtomylist.setOnClickListener {
            Toast.makeText(requireContext(), "Added to my list", Toast.LENGTH_SHORT).show()
        }
        // Inflate the layout for this fragment
        return binding.root
    }


}