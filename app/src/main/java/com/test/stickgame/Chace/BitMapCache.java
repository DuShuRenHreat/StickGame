package com.test.stickgame.Chace;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.HashMap;

/**
 * Created by asdf on 2017/4/24.
 */

public class BitMapCache {
    private static Bitmap bitmap = null;
    private static Context context;
    public BitMapCache(Context context){
        this.context = context;
    }
    private static HashMap<Integer,Bitmap> cache  = new HashMap<>();
    public static Bitmap create(int resId){
        for(Integer i : cache.keySet()){
            return cache.get(i);
        }
        bitmap = BitmapFactory.decodeResource(context.getResources(),resId);
        cache.put(resId,bitmap);
        return bitmap;
    }
}
