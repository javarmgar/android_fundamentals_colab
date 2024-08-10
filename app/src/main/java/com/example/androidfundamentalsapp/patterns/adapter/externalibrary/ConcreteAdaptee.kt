package com.example.androidfundamentalsapp.patterns.adapter.externalibrary

import android.util.Log
import com.example.androidfundamentalsapp.patterns.adapter.Client.Companion.DP_ADAPTER_TAG
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConcreteAdaptee @Inject constructor(): Adaptee
{
    override fun method1Adaptee() {
        Log.d(DP_ADAPTER_TAG, "method1Adaptee")
    }

    override fun method2Adaptee() {
        Log.d(DP_ADAPTER_TAG, "method2Adaptee")
    }

}