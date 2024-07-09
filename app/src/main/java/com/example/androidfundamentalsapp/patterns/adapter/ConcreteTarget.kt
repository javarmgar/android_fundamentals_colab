package com.example.androidfundamentalsapp.patterns.adapter

import android.util.Log
import com.example.androidfundamentalsapp.patterns.adapter.Client.Companion.DP_ADAPTER_TAG

class ConcreteTarget: TargetExample{

    override fun firstTargetMethod() {
        Log.d(DP_ADAPTER_TAG,"firstTargetMethod")
    }

    override fun secondTargetMethod() {
        Log.d(DP_ADAPTER_TAG,"secondTargetMethod")
    }

}
