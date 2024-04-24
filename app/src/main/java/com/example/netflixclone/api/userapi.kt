package com.example.netflixclone.api

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface userapi {
    @POST("/user/register")
    suspend fun registeruser(@Body userrequest: userrequest):userresponse

    @POST("/user/login")
    suspend fun loginuser(@Body userrequest: userrequest):userresponse

    @GET("/user/getmovie")
    suspend fun getmovie():ArrayList<movieresponseItem>

    @GET("/user/getmoviecategory/{category}")
    suspend fun getmoviecategory(@Path("category") category:String):ArrayList<movieresponseItem>

    @GET("/user/getthreemovie")
    suspend fun getthreemovie():ArrayList<movieresponseItem>

}