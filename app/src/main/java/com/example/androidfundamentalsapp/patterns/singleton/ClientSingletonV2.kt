package com.example.androidfundamentalsapp.patterns.singleton

import android.util.Log

class ClientSingletonV2 {

    companion object{
        val LOG_TAG = "ClientSingletonV2"
        fun main(){
            val instanceOne = SingletonV2.getInstance()
            val instanceTwo = SingletonV2.getInstance()
            val instanceThree = SingletonV2.getInstance()
            Log.d(LOG_TAG,"${instanceOne?.counter}")
            instanceTwo?.increaseCounter()
            instanceThree?.increaseCounter()
            instanceOne?.increaseCounter()
            Log.d(LOG_TAG,"${instanceOne?.counter}")



        }
    }
}