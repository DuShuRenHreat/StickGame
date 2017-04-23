package com.test.stickgame.utils;

import android.app.Activity;

import java.lang.reflect.Field;

/**
 * Created by asdf on 2017/4/23.
 */

public class InitView {
    public static void load(Activity context){
        Class<? extends Activity> tClass = context.getClass();
        for (Field f : tClass.getDeclaredFields()){
            InitID init = f.getAnnotation(InitID.class);
            if(init != null){
                int id = init.value();
                if(id != -1){
                    Object obj  = context.findViewById(id);
                    f.setAccessible(true);
                    try {
                        f.set(context,obj);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
