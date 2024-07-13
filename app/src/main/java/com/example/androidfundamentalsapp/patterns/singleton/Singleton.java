package com.example.androidfundamentalsapp.patterns.singleton;

public class Singleton {
    private Singleton(){}
    private int counter = 0;
    private static Singleton singleton = null;
    public static Singleton getInstance(){
        if(singleton == null){
            singleton = new Singleton();
        }
        return  singleton;
    }

    public void increaseCounter(){
        counter++;
    }
    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
