package com.example.androidfundamentalsapp.patterns.singleton;

import android.util.Log;

public class ClientSingleton {
    private static String LOG_TAG = "ClientSingleton";

    public static void main(){
        Singleton instanceOne = Singleton.getInstance();
        Singleton instanceTwo = Singleton.getInstance();
        Singleton instanceThree = Singleton.getInstance();
        Log.d(LOG_TAG,instanceOne.getCounter() + "");
        instanceOne.increaseCounter();
        instanceTwo.increaseCounter();
        instanceThree.increaseCounter();
        Log.d(LOG_TAG,instanceOne.getCounter() + "");

    }
}