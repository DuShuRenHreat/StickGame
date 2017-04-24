package com.test.stickgame;

import android.app.Application;

import com.test.stickgame.Chace.BitMapCache;

/**
 * Created by asdf on 2017/4/24.
 */

public class EAppliaction extends Application {
    public BitMapCache cache;
    @Override
    public void onCreate() {
        super.onCreate();
        cache = new BitMapCache(this);
    }
}
