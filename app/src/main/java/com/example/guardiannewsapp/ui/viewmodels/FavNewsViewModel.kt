package com.example.guardiannewsapp.ui.viewmodels

import androidx.lifecycle.LiveData

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.guardiannewsapp.network.models.Result
import com.example.guardiannewsapp.network.repository.GuardianRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class FavNewsViewModel(application: Application) : AndroidViewModel(application) {
    private val mRepository: GuardianRepository = GuardianRepository(application)
    private val mFavNews: LiveData<List<Result>> = mRepository.favNews

      suspend fun insertResultToDB(result: Result) {
        viewModelScope.launch(Dispatchers.IO) {
            mRepository.insertResult(result)
        }

    }

    fun getAllFavNews(): LiveData<List<Result>> {
        return mFavNews
    }

}