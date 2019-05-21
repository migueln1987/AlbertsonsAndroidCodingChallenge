package com.example.albertsonsandroidcodingchallenge.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.albertsonsandroidcodingchallenge.R
import com.example.albertsonsandroidcodingchallenge.databinding.ActivityMainBinding
import com.example.albertsonsandroidcodingchallenge.viewmodels.dictionary.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityMainBinding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        activityMainBinding.viewModel = mainViewModel
        activityMainBinding.lifecycleOwner = this
        var editTextAcronym=activityMainBinding.editTextAcronym
        editTextAcronym.text.toString()
    }

    override fun onStart() {
        super.onStart()
        mainViewModel.onStart(this)
    }

    override fun onStop() {
        super.onStop()
        mainViewModel.onStop(this)
    }
}

