package com.example.androidfundamentalsapp.dagger

import com.example.androidfundamentalsapp.MainActivity
import com.example.androidfundamentalsapp.dagger.modules.NetworkModule
import com.example.androidfundamentalsapp.retrofitmvvm.view.RetrofitActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface ApplicationContainer {
    //fun client(): Client
    fun inject(mainActivity: MainActivity)
    fun inject(retrofitActivity: RetrofitActivity)

}
