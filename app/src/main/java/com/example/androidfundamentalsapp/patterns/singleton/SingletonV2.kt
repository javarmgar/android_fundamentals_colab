package com.example.androidfundamentalsapp.patterns.singleton

class SingletonV2 private constructor(){
    var counter:Int = 0

    fun increaseCounter(){
        counter++
    }
    companion object{
        var singleton:SingletonV2? = null
        fun getInstance(): SingletonV2? {
            if(singleton == null){
                singleton = SingletonV2()
            }
            return singleton
        }
    }
}