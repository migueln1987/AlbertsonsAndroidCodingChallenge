package com.example.albertsonsandroidcodingchallenge.viewmodels

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner

abstract class CustomAndroidViewModel(application: Application) : AndroidViewModel(application) {

    val dataLoading = ObservableBoolean()

    abstract fun onStart(owner: LifecycleOwner)
    abstract fun onStop(owner: LifecycleOwner)
}
