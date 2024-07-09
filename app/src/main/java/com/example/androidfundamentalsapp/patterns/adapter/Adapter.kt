package com.example.androidfundamentalsapp.patterns.adapter

import com.example.androidfundamentalsapp.patterns.adapter.externalibrary.Adaptee

class Adapter(private val adaptee: Adaptee):TargetExample {

    override fun firstTargetMethod() {
        adaptee.method1Adaptee()
    }

    override fun secondTargetMethod() {
        adaptee.method2Adaptee()
    }

}