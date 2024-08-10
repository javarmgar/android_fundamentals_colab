package com.example.androidfundamentalsapp.dagger

import com.example.androidfundamentalsapp.patterns.adapter.Client
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component
interface ApplicationContainer {

    fun client(): Client

}