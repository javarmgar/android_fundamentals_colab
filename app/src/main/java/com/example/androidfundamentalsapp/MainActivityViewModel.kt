package com.example.androidfundamentalsapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import patterns.observer.ConcreteObservee
import patterns.observer.Observee
import patterns.observer.Persona

class MainActivityViewModel : ViewModel() {
    var variable: String = "TheViewModelTextView"
    private val TAG = "MainActivityViewModel"
    val observee:ConcreteObservee = ConcreteObservee()

    val personaLiveData:MutableLiveData<Persona> = MutableLiveData(Persona("Havier Guadalupe",22))

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG,"onCleared()")
    }


    fun changeVariable(s: String) {
        variable = s
    }

    fun getDatabaseName():String{
        return Database.DB_NAME
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
