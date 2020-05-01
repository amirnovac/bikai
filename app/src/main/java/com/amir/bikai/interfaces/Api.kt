package com.amir.bikai.interfaces

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("prize.json")
     fun getPrizes(): Call<ResponseBody>


}