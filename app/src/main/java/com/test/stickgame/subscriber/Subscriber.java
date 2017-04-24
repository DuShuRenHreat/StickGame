package com.test.stickgame.subscriber;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class Subscriber {
    private Object context;
    private Method method;
    public Subscriber(Object object,Method method){
        this.context = object;
        this.method = method;
    }
    public Object getContext(){
        return context;
    }

    public void post(Object message){
        try {
            method.invoke(context,message);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
