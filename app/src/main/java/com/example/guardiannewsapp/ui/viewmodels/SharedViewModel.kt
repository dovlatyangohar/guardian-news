package com.example.guardiannewsapp.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.guardiannewsapp.network.models.Result

class SharedViewModel : ViewModel() {
    val selected = MutableLiveData<Result>()

}
