package com.test.stickgame;

import android.app.Application;

import com.test.stickgame.Chace.BitMapCache;


public class EAppliaction extends Application {
    public BitMapCache cache;
    @Override
    public void onCreate() {
        super.onCreate();
        cache = new BitMapCache(this);
    }
}
