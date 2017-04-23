package com.test.stickgame.state;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by asdf on 2017/4/23.
 */

public abstract class Statable  {
    public Paint paint;
    public Statable(){
        paint = new Paint();
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
    }
    public abstract void onDraw(Canvas canvas);
}
