package com.amir.bikai.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amir.bikai.interfaces.Api
import com.amir.bikai.interfaces.ApiListener
import com.amir.bikai.interfaces.Constants
import com.amir.bikai.model.PrizeModel
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PrizesViewModel() : ViewModel(),Constants{

    //this is the data that we will fetch asynchronously
    private var prizes: MutableLiveData<List<PrizeModel.Prize>>? = null


   /* fun getListener(apiListener: ApiListener) {
        this.apiListener = apiListener
    }*/

    //we will call this method to get the data
    fun getPrizes(): LiveData<List<PrizeModel.Prize>> {
        //if the list is null
        if (prizes == null) {
            prizes = MutableLiveData<List<PrizeModel.Prize>>()
            //we will load it asynchronously from server in this method
            loadPrizes()
        }

        //finally we will return the list
        return prizes as MutableLiveData<List<PrizeModel.Prize>>
    }

    private fun loadPrizes() {
       // apiListener.onStarting()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(Api::class.java!!)
        val call = api.getPrizes()

        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                try {
                    if (response.isSuccessful) {
                        val type = object : TypeToken<PrizeModel>() {

                        }.type
                        val gson = GsonBuilder().setLenient().create()
                        val data = gson.fromJson<PrizeModel>(response.body()!!.string(), type)
                        prizes!!.value = data.getPrizes()
                       // apiListener.onSucces()
                    }
                    else{
                        //apiListener.onFailure("Something went wrong")
                    }
                } catch (ex: Exception) {
                    ex.printStackTrace()
                  //  apiListener.onFailure("Something went wrong")
                }

            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
            //    apiListener.onFailure("Something went wrong")
            }
        })
    }
}