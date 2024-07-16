package com.example.androidfundamentalsapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidfundamentalsapp.Database
import com.example.androidfundamentalsapp.patterns.observer.ConcreteObservee
import com.example.androidfundamentalsapp.patterns.observer.Persona

class MainActivityViewModel : ViewModel() {
    var variable: String = "TheViewModelTextView"
    private val TAG = "MainActivityViewModel"
    val observee: ConcreteObservee = ConcreteObservee()

    private val _personaLiveData: MutableLiveData<Persona> = MutableLiveData(Persona("Havier Guadalupe",22))
    val personaLiveData: LiveData<Persona> = _personaLiveData

    fun changeVariable(s: String) {
        variable = s
    }

    fun getDatabaseName():String{
        return Database.DB_NAME
    }

    override fun onCleared(){
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

        Log.d(TAG,"updateLiveData -> on main thread")
        val thread = Thread(
            null,
            object : Runnable {
                override fun run() {
                    Thread.sleep(5000L)
                    val newPersona = Persona(personaLiveData.value?.name?:"Jabo Default",(personaLiveData.value?.age?:0)+2)
                    //this cannot be execute in another thread, it assumes that .value (the update) is being done on the same thread
                    /*
                    FATAL EXCEPTION: choreThread
                        Process: com.example.androidfundamentalsapp, PID: 2351
                        java.lang.IllegalStateException: Cannot invoke setValue on a background thread
                     */
                    //_personaLiveData.value = newPersona
                    _personaLiveData.postValue(newPersona)
                    Log.d(TAG,"updateLiveData -> updating on background thread new value: ${_personaLiveData.value} Thread:${Thread.currentThread().name}")

                }
            },
            "choreThread")
        thread.start()

    }
}
