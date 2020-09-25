package com.example.diseaseoutbreaks.ui

import android.util.Log
import androidx.lifecycle.ViewModel

class DiseasesViewModel : ViewModel() {

    init {
        Log.i("Disease viewModel : ", "Disease viewModel Created")
    }


    override fun onCleared() {
        super.onCleared()
        Log.i("Disease viewModel : ", "Disease viewModel Destroyed")
    }
}