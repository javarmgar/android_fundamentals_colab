package com.example.androidfundamentalsapp.patterns.adapter

import android.util.Log
import com.example.androidfundamentalsapp.patterns.adapter.Client.Companion.DP_ADAPTER_TAG
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConcreteTarget @Inject constructor(): TargetExample{

    override fun firstTargetMethod() {
        Log.d(DP_ADAPTER_TAG,"firstTargetMethod")
    }

    override fun secondTargetMethod() {
        Log.d(DP_ADAPTER_TAG,"secondTargetMethod")
    }

}
