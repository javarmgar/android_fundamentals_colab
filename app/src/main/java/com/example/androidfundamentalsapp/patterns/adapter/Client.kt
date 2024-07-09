package com.example.androidfundamentalsapp.patterns.adapter

import com.example.androidfundamentalsapp.patterns.adapter.externalibrary.Adaptee
import com.example.androidfundamentalsapp.patterns.adapter.externalibrary.ConcreteAdaptee

class Client {
    fun methodName(){
        val concreteTarget: TargetExample = ConcreteTarget()
        concreteTarget.firstTargetMethod()
        concreteTarget.secondTargetMethod()

        val adaptee: Adaptee =  ConcreteAdaptee()
        val target:TargetExample = Adapter(adaptee)
        target.firstTargetMethod()
        target.secondTargetMethod()
    }

    companion object{
        val DP_ADAPTER_TAG: String = "AdapterPattern"
    }


}