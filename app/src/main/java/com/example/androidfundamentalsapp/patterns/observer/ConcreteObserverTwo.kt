package com.example.androidfundamentalsapp.patterns.observer

import android.util.Log

class ConcreteObserverTwo : Observer {
    private val TAG = "ConcreteObserverTwo"
    override fun update(value: Persona) {
        Log.d(TAG,"$value")
    }
}