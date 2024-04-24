package com.example.netflixclone.models

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("downloads")
data class downloadclass(
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    val moviename:String,
    val moviedescription:String,
    val imagepath:String,
    val moviepath:String
)