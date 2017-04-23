package com.test.stickgame.state;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import java.util.HashMap;

public class StateView extends View {
    private HashMap<String,Statable> states = new HashMap<>();
    private String key  = null;
    public StateView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.states.get(key).onDraw(canvas);
    }

    public void addState(String key,Statable state){
        states.put(key,state);
    }
    public void setState(String key){
        this.key = key;
    }
}
