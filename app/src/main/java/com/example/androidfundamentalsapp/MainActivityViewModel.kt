package com.example.androidfundamentalsapp

import android.util.Log
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    var variable: String = "TheViewModelTextView"
    private val TAG = "MainActivityViewModel"

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG,"onCleared()")
    }

    fun changeVariable(s: String) {
        variable = s
    }

}
