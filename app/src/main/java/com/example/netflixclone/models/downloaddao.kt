package com.example.netflixclone.models

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface downloaddao {
  @Insert
   fun putdownload(downloadclass: downloadclass)

  @Query("SELECT * FROM downloads ORDER BY ID ASC")
   fun getdownload():List<downloadclass>

   @Delete
   fun deletedata(downloadclass: downloadclass);
}