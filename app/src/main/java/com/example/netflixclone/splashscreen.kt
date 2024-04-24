package com.example.netflixclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class splashscreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)
       Handler().postDelayed(
           {
           val a=Intent(this,MainActivity::class.java)
               startActivity(a)
              finish()
           },
           4000
       )
    }
}