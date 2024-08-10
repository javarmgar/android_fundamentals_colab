package com.example.androidfundamentalsapp

import android.app.Application
import com.example.androidfundamentalsapp.dagger.ApplicationContainer
import com.example.androidfundamentalsapp.dagger.DaggerApplicationContainer

class MyApplication : Application() {

    val applicationContainer: ApplicationContainer = DaggerApplicationContainer.create()

}