package com.example.androidfundamentalsapp.patterns.adapter

import com.example.androidfundamentalsapp.patterns.adapter.externalibrary.Adaptee
import com.example.androidfundamentalsapp.patterns.adapter.externalibrary.ConcreteAdaptee
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Client @Inject constructor(
    private val concreteTarget: ConcreteTarget,
    private val adaptee: ConcreteAdaptee,
) {
    fun methodName(){
        val concreteTarget: TargetExample = this.concreteTarget
        concreteTarget.firstTargetMethod()
        concreteTarget.secondTargetMethod()

        val adaptee: Adaptee =  this.adaptee
        val target:TargetExample = Adapter(adaptee)
        target.firstTargetMethod()
        target.secondTargetMethod()
    }

    companion object{
        val DP_ADAPTER_TAG: String = "AdapterPattern"
    }


}