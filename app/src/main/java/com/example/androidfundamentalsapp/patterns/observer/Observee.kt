package com.example.androidfundamentalsapp.patterns.observer

interface Observee {

    val observers: MutableList<Observer>
    fun addObserver(observer: Observer)
    fun removeObserver(observer: Observer)
    fun notifyObservers()
}