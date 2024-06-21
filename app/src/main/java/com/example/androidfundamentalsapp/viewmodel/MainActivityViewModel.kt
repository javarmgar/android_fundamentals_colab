package com.example.androidfundamentalsapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidfundamentalsapp.Database
import patterns.observer.ConcreteObservee
import patterns.observer.Persona

class MainActivityViewModel : ViewModel() {
    var variable: String = "TheViewModelTextView"
    private val TAG = "MainActivityViewModel"
    val observee:ConcreteObservee = ConcreteObservee()
    val personaLiveData:MutableLiveData<Persona> = MutableLiveData(Persona("Havier Guadalupe",22))

    fun changeVariable(s: String) {
        variable = s
    }

    fun getDatabaseName():String{
        return Database.DB_NAME
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG,"onCleared()")
    }

    fun updateObservee(){
        observee.updateAge()
    }

    fun updateLiveData(){
//        val currJavi: Persona? = personaLiveData.value
//        personaLiveData.value = currJavi?.apply {
//            age += 2
//
//        }
        val newPersona = Persona(personaLiveData.value?.name?:"Jabo Default",(personaLiveData.value?.age?:0)+2)
        personaLiveData.value = newPersona
    }
}
