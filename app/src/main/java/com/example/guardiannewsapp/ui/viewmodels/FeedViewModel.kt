package com.example.guardiannewsapp.ui.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.guardiannewsapp.network.models.Result
import com.example.guardiannewsapp.network.repository.GuardianRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FeedViewModel(application: Application) : AndroidViewModel(application) {
    private val mRepository: GuardianRepository = GuardianRepository(application)
    private val newsList = MutableLiveData<List<Result>>()

    suspend fun getNews():LiveData<List<Result>> {
        newsList.postValue(mRepository.getAllResultsFromNetwork())
        return newsList
    }
}