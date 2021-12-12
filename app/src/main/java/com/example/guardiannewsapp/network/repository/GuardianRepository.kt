package com.example.guardiannewsapp.network.repository

import androidx.lifecycle.LiveData
import android.content.Context
import com.example.guardiannewsapp.db.GuardianAppDao
import com.example.guardiannewsapp.db.GuardianAppDataBase
import com.example.guardiannewsapp.network.api.GuardianService
import com.example.guardiannewsapp.network.api.GuardianService.Companion.BASE_URL
import com.example.guardiannewsapp.network.models.GuardianNews
import com.example.guardiannewsapp.network.models.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class GuardianRepository(context: Context) {
    private var mGuardianAppDao: GuardianAppDao
    private var mFavNews: LiveData<List<Result>>
    private lateinit var news: LiveData<List<Result>>

    
    val favNews: LiveData<List<Result>>
        get() = mFavNews

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val service = retrofit.create(GuardianService::class.java)


    fun insertResult(result: Result) {
        mGuardianAppDao.insertResult(result)
    }

    init {
        val db: GuardianAppDataBase = GuardianAppDataBase.getDatabaseInstance(context)
        mGuardianAppDao = db.getGuardianDao()
        mFavNews = mGuardianAppDao.getAllResultsFromDb()
    }

    suspend fun getAllResultsFromNetwork(): List<Result> {
        val call: Call<GuardianNews> = service.getDataFromApi()
        return withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            val response: Response<GuardianNews> = call.execute()
            val feed: GuardianNews = response.body()!!
            feed.response.results
        }
    }

}