package com.test.stickgame.Chace;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by asdf on 2017/4/24.
 */

public class BitMapCache {

    private static Context context;
    public BitMapCache(Context context){
        this.context = context;
    }
    private static HashMap<Integer,Bitmap> cache  = new HashMap<>();
    public static Bitmap create(int resId){
        for(Integer i : cache.keySet()){
            if(i == resId){
                return cache.get(resId);
            }
        }
        Bitmap bitmap = null;
        bitmap = BitmapFactory.decodeResource(context.getResources(),resId);
        cache.put(resId,bitmap);
        Log.e("test","已缓存");
        return bitmap;
    }
}
