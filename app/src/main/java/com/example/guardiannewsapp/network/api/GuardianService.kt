package com.example.guardiannewsapp.network.api

import com.example.guardiannewsapp.network.models.GuardianNews
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface GuardianService {
    companion object {
        const val BASE_URL = "https://content.guardianapis.com"
        const val API_KEY = "1726cb7d-e174-46c3-aa73-7822617184a8"
//        private var retrofitService: GuardianService? = null

//        fun getInstance(): GuardianService{
//            if(retrofitService == null){
//
//                 val retrofit: Retrofit = Retrofit.Builder()
//                    .baseUrl(BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build()
//                retrofitService = retrofit.create(GuardianService::class.java)
//            }
//            return retrofitService!!
//
//        }
    }

    @GET("/search?api-key=$API_KEY")
    fun getDataFromApi(): Call<GuardianNews>
}