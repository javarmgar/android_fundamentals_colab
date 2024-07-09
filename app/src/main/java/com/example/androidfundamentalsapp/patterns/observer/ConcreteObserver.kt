package com.example.androidfundamentalsapp.patterns.observer

import android.util.Log

class ConcreteObserver : Observer {
    private val TAG = "ConcreteObserver"
    override fun update(value: Persona) {
        Log.d(TAG,"$value")
    }
}